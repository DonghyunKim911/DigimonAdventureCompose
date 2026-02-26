package com.dis.presentation.bookmark

import androidx.lifecycle.viewModelScope
import com.core.presentation.base.BaseViewModel
import com.core.presentation.mapper.toPresentation
import com.dis.domain.digimon.usecase.GetBookmarkDigimonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel
    @Inject
    constructor(
        getBookmarkDigimonListUseCase: GetBookmarkDigimonListUseCase,
    ) : BaseViewModel<BookmarkAction, BookmarkEvent>() {
        val state: StateFlow<BookmarkUiState> =
            getBookmarkDigimonListUseCase()
                .map { digimons ->
                    BookmarkUiState(
                        isLoading = false,
                        digimonList = digimons.map { it.toPresentation() },
                    )
                }.onStart {
                    emit(BookmarkUiState(isLoading = true))
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = BookmarkUiState(),
                )

        override fun onAction(action: BookmarkAction) {
            when (action) {
                is BookmarkAction.OnDigimonClick -> {
                    _eventChannel.trySend(BookmarkEvent.NavigateToDigimonDetail(action.id))
                }
            }
        }
    }
