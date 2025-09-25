package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Content
import com.dis.domain.digimon.model.DigimonList
import com.dis.domain.digimon.model.Pageable
import com.dis.presentation.model.DigimonListModel

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