package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Field
import com.dis.presentation.model.FieldModel

fun Field.toPresentation(): FieldModel = FieldModel(
    field = field,
    id = id,
    image = image
)

fun FieldModel.toDomain(): Field = Field(
    field = field,
    id = id,
    image = image
)
