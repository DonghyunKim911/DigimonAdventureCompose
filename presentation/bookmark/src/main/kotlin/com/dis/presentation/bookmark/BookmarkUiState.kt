package com.dis.presentation.bookmark

import androidx.compose.runtime.Stable
import com.core.presentation.model.ContentModel

@Stable
data class BookmarkUiState(
    val isLoading: Boolean = false,
    val digimonList: List<ContentModel> = emptyList(),
)
