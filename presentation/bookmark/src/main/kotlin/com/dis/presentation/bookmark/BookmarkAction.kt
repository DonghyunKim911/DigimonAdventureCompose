package com.dis.presentation.bookmark

import com.core.presentation.base.ViewAction

sealed interface BookmarkAction : ViewAction {
    data class OnDigimonClick(
        val id: Int,
    ) : BookmarkAction
}
