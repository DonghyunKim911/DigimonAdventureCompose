package com.core.presentation.mapper

import com.core.presentation.model.TypeModel
import com.dis.core.domain.model.Type

fun Type.toPresentation(): TypeModel = TypeModel(
    id = id,
    type = type
)

fun TypeModel.toDomain(): Type = Type(
    id = id,
    type = type
)
