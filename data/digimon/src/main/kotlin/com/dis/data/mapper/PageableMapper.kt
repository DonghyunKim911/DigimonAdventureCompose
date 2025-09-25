package com.dis.data.mapper

import com.dis.data.model.PageableData
import com.dis.domain.digimon.model.Pageable

fun PageableData.toDomain(): Pageable = Pageable(
    currentPage = currentPage,
    elementsOnPage = elementsOnPage,
    nextPage = nextPage,
    previousPage = previousPage,
    totalElements = totalElements,
    totalPages = totalPages
)

fun Pageable.toData(): PageableData = PageableData(
    currentPage = currentPage,
    elementsOnPage = elementsOnPage,
    nextPage = nextPage,
    previousPage = previousPage,
    totalElements = totalElements,
    totalPages = totalPages
)
