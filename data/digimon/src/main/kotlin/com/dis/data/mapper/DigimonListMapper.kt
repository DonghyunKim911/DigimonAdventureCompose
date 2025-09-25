package com.dis.data.mapper

import com.dis.data.model.ContentData
import com.dis.data.model.DigimonListData
import com.dis.data.model.PageableData
import com.dis.domain.digimon.model.DigimonList

fun DigimonListData.toDomain(): DigimonList = DigimonList(
    content = contents?.map { it?.toDomain() },
    pageable = pageable?.toDomain()
)

fun DigimonList.toData(): DigimonListData = DigimonListData(
    contents = content?.map {
        ContentData(
            id = it?.id ?: -1,
            href = it?.href,
            name = it?.name,
            image = it?.image
        )
    },
    pageable = PageableData(
        currentPage = pageable?.currentPage,
        elementsOnPage = pageable?.elementsOnPage,
        nextPage = pageable?.nextPage,
        previousPage = pageable?.previousPage,
        totalElements = pageable?.totalElements,
        totalPages = pageable?.totalPages
    )
)