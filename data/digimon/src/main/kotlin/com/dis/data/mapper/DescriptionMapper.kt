package com.dis.data.mapper

import com.dis.data.model.DescriptionData
import com.dis.domain.digimon.model.Description

fun DescriptionData.toDomain(): Description = Description(
    description = description,
    language = language,
    origin = origin
)


fun Description.toData(): DescriptionData = DescriptionData(
    description = description,
    language = language,
    origin = origin
)
