package com.dis.presentation.home

import com.core.presentation.base.ViewEvent

sealed interface HomeEvent : ViewEvent {
    data class NavigateToDigimonDetail(
        val id: Int,
    ) : HomeEvent
}
