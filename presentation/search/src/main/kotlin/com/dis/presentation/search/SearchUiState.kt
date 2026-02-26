package com.dis.presentation.search

import androidx.compose.runtime.Stable
import com.core.presentation.model.ContentModel

@Stable
data class SearchUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val digimonList: List<ContentModel> = emptyList(),
    val isLastPageReached: Boolean = false,
)
