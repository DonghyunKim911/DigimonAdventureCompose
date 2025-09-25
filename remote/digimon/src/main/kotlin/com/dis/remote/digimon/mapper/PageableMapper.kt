package com.dis.remote.digimon.mapper

import com.dis.data.model.PageableData
import com.dis.remote.digimon.model.PageableResponse

fun PageableResponse.toData(): PageableData = PageableData(
    currentPage = currentPage,
    elementsOnPage = elementsOnPage,
    nextPage = nextPage,
    previousPage = previousPage,
    totalElements = totalElements,
    totalPages = totalPages
)
