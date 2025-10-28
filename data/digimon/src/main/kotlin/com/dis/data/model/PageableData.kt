package com.dis.data.model

data class PageableData(
    val currentPage: Int?,
    val elementsOnPage: Int?,
    val nextPage: String?,
    val previousPage: String?,
    val totalElements: Int?,
    val totalPages: Int?,
)
