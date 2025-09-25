package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Type
import com.dis.presentation.model.TypeModel

fun Type.toPresentation(): TypeModel = TypeModel(
    id = id,
    type = type
)

fun TypeModel.toDomain(): Type = Type(
    id = id,
    type = type
)
