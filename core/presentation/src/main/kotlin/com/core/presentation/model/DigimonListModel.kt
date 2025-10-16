package com.core.presentation.model

/**
 * Used for getting Digimon list.
 * */
data class DigimonListModel(
    val content: List<ContentModel?>?,
    val pageable: PageableModel? // Not used in this project.
)