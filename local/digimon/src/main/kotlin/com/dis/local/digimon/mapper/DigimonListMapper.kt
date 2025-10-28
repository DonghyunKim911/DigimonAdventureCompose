package com.dis.local.digimon.mapper

import com.dis.core.database.entity.DigimonListEntity
import com.dis.data.model.DigimonListData

fun DigimonListEntity.toDigimonList(): DigimonListData =
    DigimonListData(
        contents = contents?.map { it?.toData() },
        pageable = pageable?.toData(),
    )

// fun DigimonListData.toLocal(): DigimonListEntity = DigimonListEntity(
//    contents = contents?.map {
//        ContentEntity(
//            id = it?.id,
//            href = it?.href,
//            name = it?.name,
//            image = it?.image,
//            page = it?.page,
//        )
//    },
//    pageable = PageableEntity(
//        currentPage = pageable?.currentPage,
//        elementsOnPage = pageable?.elementsOnPage,
//        nextPage = pageable?.nextPage,
//        previousPage = pageable?.previousPage,
//        totalElements = pageable?.totalElements,
//        totalPages = pageable?.totalPages
//    )
// )
