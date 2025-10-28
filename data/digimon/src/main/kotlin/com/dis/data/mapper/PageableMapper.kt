package com.dis.data.mapper

import com.dis.core.domain.model.Pageable
import com.dis.data.model.PageableData

fun PageableData.toDomain(): Pageable =
    Pageable(
        currentPage = currentPage,
        elementsOnPage = elementsOnPage,
        nextPage = nextPage,
        previousPage = previousPage,
        totalElements = totalElements,
        totalPages = totalPages,
    )

fun Pageable.toData(): PageableData =
    PageableData(
        currentPage = currentPage,
        elementsOnPage = elementsOnPage,
        nextPage = nextPage,
        previousPage = previousPage,
        totalElements = totalElements,
        totalPages = totalPages,
    )
