package com.dis.presentation.search

import com.core.presentation.base.ViewAction

sealed interface SearchAction : ViewAction {
    data class OnDigimonClick(
        val id: Int,
    ) : SearchAction

    data class OnQueryChanged(
        val query: String,
    ) : SearchAction

    data object OnFetchNextDigimonList : SearchAction
}
