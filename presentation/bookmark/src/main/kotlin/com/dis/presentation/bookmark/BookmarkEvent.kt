package com.dis.presentation.bookmark

import com.core.presentation.base.ViewEvent

sealed interface BookmarkEvent : ViewEvent {
    data class NavigateToDigimonDetail(
        val id: Int,
    ) : BookmarkEvent
}
