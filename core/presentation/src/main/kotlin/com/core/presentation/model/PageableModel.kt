package com.core.presentation.model

/**
 * Used for getting Digimon list.
 * */
data class PageableModel(
    val currentPage: Int?,
    val elementsOnPage: Int?,
    val nextPage: String?,
    val previousPage: String?,
    val totalElements: Int?,
    val totalPages: Int?
)
