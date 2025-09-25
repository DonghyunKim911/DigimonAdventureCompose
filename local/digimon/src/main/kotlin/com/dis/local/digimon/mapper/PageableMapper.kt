package com.dis.local.digimon.mapper

import com.dis.core.database.entity.PageableEntity
import com.dis.data.model.PageableData

fun PageableEntity.toData(): PageableData = PageableData(
    currentPage = currentPage,
    elementsOnPage = elementsOnPage,
    nextPage = nextPage,
    previousPage = previousPage,
    totalElements = totalElements,
    totalPages = totalPages
)

fun PageableData.toLocal(): PageableEntity = PageableEntity(
    currentPage = currentPage,
    elementsOnPage = elementsOnPage,
    nextPage = nextPage,
    previousPage = previousPage,
    totalElements = totalElements,
    totalPages = totalPages
)
