package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Pageable
import com.dis.presentation.model.PageableModel

fun Pageable.toPresentation(): PageableModel = PageableModel(
    currentPage = currentPage,
    elementsOnPage = elementsOnPage,
    nextPage = nextPage,
    previousPage = previousPage,
    totalElements = totalElements,
    totalPages = totalPages
)

fun PageableModel.toDomain(): Pageable = Pageable(
    currentPage = currentPage,
    elementsOnPage = elementsOnPage,
    nextPage = nextPage,
    previousPage = previousPage,
    totalElements = totalElements,
    totalPages = totalPages
)
