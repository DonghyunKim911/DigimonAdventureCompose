package com.dis.presentation.home

import com.core.presentation.base.ViewAction

sealed interface HomeAction : ViewAction {
    data class OnDigimonClick(
        val id: Int,
    ) : HomeAction

    data object OnFetchNextDigimonList : HomeAction
}
