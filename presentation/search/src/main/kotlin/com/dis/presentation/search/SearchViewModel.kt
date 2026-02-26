package com.dis.presentation.search

import androidx.lifecycle.viewModelScope
import com.core.presentation.base.BaseViewModel
import com.core.presentation.mapper.toPresentation
import com.core.presentation.model.ContentModel
import com.dis.domain.digimon.usecase.GetDigimonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel
    @Inject
    constructor(
        private val getDigimonListUseCase: GetDigimonListUseCase,
    ) : BaseViewModel<SearchAction, SearchEvent>() {
        private val pagingIndex = MutableStateFlow(NO_PAGE)
        private val query = MutableStateFlow("")
        private val allDigimons = MutableStateFlow<PersistentList<ContentModel>>(persistentListOf())
        private val isLoading = MutableStateFlow(false)
        private val isLastPageReached = MutableStateFlow(false)

        val state: StateFlow<SearchUiState> =
            combine(
                query,
                query.debounce(SEARCH_DEBOUNCE_MILLIS).distinctUntilChanged(),
                allDigimons,
                isLoading,
                isLastPageReached,
            ) { currentQuery, debouncedQuery, digimons, loading, lastPageReached ->
                SearchUiState(
                    isLoading = loading,
                    query = currentQuery,
                    digimonList = filterDigimons(digimons, debouncedQuery),
                    isLastPageReached = lastPageReached,
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchUiState(),
            )

        init {
            observePaging()
        }

        override fun onAction(action: SearchAction) {
            when (action) {
                is SearchAction.OnDigimonClick -> {
                    _eventChannel.trySend(SearchEvent.NavigateToDigimonDetail(action.id))
                }

                is SearchAction.OnQueryChanged -> {
                    onQueryChanged(action.query)
                }

                SearchAction.OnFetchNextDigimonList -> {
                    onFetchNextDigimonList()
                }
            }
        }

        private fun observePaging() {
            viewModelScope.launch {
                pagingIndex.collectLatest { page ->
                    if (page == NO_PAGE) return@collectLatest
                    fetchDigimonList(page)
                }
            }
        }

        private suspend fun fetchDigimonList(page: Int) {
            if (isLoading.value) return
            if (page != 0 && isLastPageReached.value) return

            isLoading.value = true

            val newItems = getDigimonListUseCase(page).first().map { it.toPresentation() }
            allDigimons.value =
                if (page == 0) {
                    newItems.toPersistentList()
                } else {
                    allDigimons.value.addAll(newItems)
                }

            isLastPageReached.value = newItems.isEmpty() || newItems.size < DEFAULT_PAGE_SIZE
            isLoading.value = false
        }

        private fun onFetchNextDigimonList() {
            if (query.value.isBlank()) return

            if (!isLoading.value && !isLastPageReached.value) {
                pagingIndex.value++
            }
        }

        private fun onQueryChanged(newQuery: String) {
            query.value = newQuery

            if (newQuery.isBlank()) return

            if (pagingIndex.value == NO_PAGE) {
                pagingIndex.value = 0
            }
        }

        private fun filterDigimons(
            digimons: List<ContentModel>,
            query: String,
        ): List<ContentModel> {
            val keyword = query.trim()
            if (keyword.isBlank()) return emptyList()

            return digimons.filter { digimon ->
                digimon.name?.contains(keyword, ignoreCase = true) == true
            }
        }

        companion object {
            const val DEFAULT_PAGE_SIZE = 20
            const val SEARCH_DEBOUNCE_MILLIS = 300L
            const val NO_PAGE = -1
        }
    }
