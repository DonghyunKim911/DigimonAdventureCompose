package com.dis.local.digimon.mapper

import com.dis.core.database.entity.TypeEntity
import com.dis.data.model.TypeData

fun TypeEntity.toData(): TypeData =
    TypeData(
        id = id,
        type = type,
    )

fun TypeData.toLocal(): TypeEntity =
    TypeEntity(
        id = id,
        type = type,
    )
