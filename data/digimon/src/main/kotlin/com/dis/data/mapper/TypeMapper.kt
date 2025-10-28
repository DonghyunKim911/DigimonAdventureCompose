package com.dis.data.mapper

import com.dis.core.domain.model.Type
import com.dis.data.model.TypeData

fun TypeData.toDomain(): Type =
    Type(
        id = id,
        type = type,
    )

fun Type.toData(): TypeData =
    TypeData(
        id = id,
        type = type,
    )
