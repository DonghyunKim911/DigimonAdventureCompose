package com.dis.data.mapper

import com.dis.data.model.TypeData
import com.dis.domain.digimon.model.Type

fun TypeData.toDomain(): Type = Type(
    id = id,
    type = type
)

fun Type.toData(): TypeData = TypeData(
    id = id,
    type = type
)
