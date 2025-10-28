package com.core.presentation.mapper

import com.core.presentation.model.FieldModel
import com.dis.core.domain.model.Field

fun Field.toPresentation(): FieldModel =
    FieldModel(
        field = field,
        id = id,
        image = image,
    )

fun FieldModel.toDomain(): Field =
    Field(
        field = field,
        id = id,
        image = image,
    )
