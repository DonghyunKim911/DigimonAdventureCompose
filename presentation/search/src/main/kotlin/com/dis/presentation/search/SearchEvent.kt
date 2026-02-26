package com.dis.presentation.search

import com.core.presentation.base.ViewEvent

sealed interface SearchEvent : ViewEvent {
    data class NavigateToDigimonDetail(
        val id: Int,
    ) : SearchEvent
}
