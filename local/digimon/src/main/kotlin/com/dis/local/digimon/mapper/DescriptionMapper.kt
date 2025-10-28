package com.dis.local.digimon.mapper

import com.dis.core.database.entity.DescriptionEntity
import com.dis.data.model.DescriptionData

fun DescriptionEntity.toData(): DescriptionData =
    DescriptionData(
        description = description,
        language = language,
        origin = origin,
    )

fun DescriptionData.toLocal(): DescriptionEntity =
    DescriptionEntity(
        description = description,
        language = language,
        origin = origin,
    )
