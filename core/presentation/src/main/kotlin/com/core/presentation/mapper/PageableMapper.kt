package com.core.presentation.mapper

import com.core.presentation.model.PageableModel
import com.dis.core.domain.model.Pageable

fun Pageable.toPresentation(): PageableModel =
    PageableModel(
        currentPage = currentPage,
        elementsOnPage = elementsOnPage,
        nextPage = nextPage,
        previousPage = previousPage,
        totalElements = totalElements,
        totalPages = totalPages,
    )

fun PageableModel.toDomain(): Pageable =
    Pageable(
        currentPage = currentPage,
        elementsOnPage = elementsOnPage,
        nextPage = nextPage,
        previousPage = previousPage,
        totalElements = totalElements,
        totalPages = totalPages,
    )
