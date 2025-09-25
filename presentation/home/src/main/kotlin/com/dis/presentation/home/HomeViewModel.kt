package com.dis.presentation.home

import androidx.lifecycle.viewModelScope
import com.core.presentation.base.BaseViewModel
import com.dis.domain.digimon.usecase.GetDigimonListUseCase
import com.dis.presentation.mapper.toPresentation
import com.dis.presentation.model.ContentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    getDigimonListUseCase: GetDigimonListUseCase
) : BaseViewModel<HomeAction, HomeEvent>() {

    private val pagingIndex = MutableStateFlow(0)
    private val allDigimons = MutableStateFlow<PersistentList<ContentModel>>(persistentListOf())
    private var lastPageReached = false

    val state: StateFlow<HomeUiState> = pagingIndex.flatMapConcat { page ->
                flow {
                    emit(
                        HomeUiState(
                            isLoading = true,
                            digimonList = allDigimons.value,
                            isLastPageReached = lastPageReached
                        )
                    )

                    val newItems = getDigimonListUseCase(page).first()
                    val mapped = newItems.map { it.toPresentation() }

                    allDigimons.value = if (page == 0) {
                        mapped.toPersistentList()
                    } else {
                        allDigimons.value.addAll(mapped)
                    }

                    lastPageReached = newItems.isEmpty() || newItems.size < DEFAULT_PAGE_SIZE

                    emit(
                        HomeUiState(
                            isLoading = false,
                            digimonList = allDigimons.value,
                            isLastPageReached = lastPageReached
                        )
                    )
                }
            }
            .onStart {
                emit(HomeUiState(isLoading = true))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeUiState()
            )

    override fun onAction(action: HomeAction) {
        when (action) {

            is HomeAction.OnDigimonClick -> {

            }

            HomeAction.OnFetchNextDigimonList -> onFetchNextDigimonList()
        }
    }

    private fun onFetchNextDigimonList() {
        if (!state.value.isLoading && !state.value.isLastPageReached) {
            pagingIndex.value++
        }
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

}
