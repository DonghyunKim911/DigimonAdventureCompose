package com.dis.data.mapper

import com.dis.core.domain.model.Description
import com.dis.data.model.DescriptionData

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
