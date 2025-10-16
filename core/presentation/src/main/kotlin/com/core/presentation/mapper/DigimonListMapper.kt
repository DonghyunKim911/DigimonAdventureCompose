package com.core.presentation.mapper

import com.core.presentation.model.DigimonListModel
import com.dis.core.domain.model.Content
import com.dis.core.domain.model.DigimonList
import com.dis.core.domain.model.Pageable

fun DigimonList.toPresentation(): DigimonListModel = DigimonListModel(
    content = content?.map { it?.toPresentation() },
    pageable = pageable?.toPresentation()
)

fun DigimonListModel.toDomain(): DigimonList = DigimonList(
    content = content?.map {
        Content(
            id = it?.id ?: -1,
            href = it?.href,
            name = it?.name,
            image = it?.image
        )
    },
    pageable = Pageable(
        currentPage = pageable?.currentPage,
        elementsOnPage = pageable?.elementsOnPage,
        nextPage = pageable?.nextPage,
        previousPage = pageable?.previousPage,
        totalElements = pageable?.totalElements,
        totalPages = pageable?.totalPages
    )
)