package com.dis.presentation.detail

import androidx.compose.runtime.Stable
import com.core.presentation.model.DigimonModel

@Stable
data class DetailUiState(
    val digimon: DigimonModel? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
