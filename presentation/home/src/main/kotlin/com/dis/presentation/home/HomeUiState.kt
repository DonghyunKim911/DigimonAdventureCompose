package com.dis.presentation.home

import androidx.compose.runtime.Stable
import com.dis.presentation.model.ContentModel

@Stable
data class HomeUiState(
    val isLoading: Boolean = false,
    val digimonList: List<ContentModel> = emptyList(),
    val isLastPageReached: Boolean = false
)
