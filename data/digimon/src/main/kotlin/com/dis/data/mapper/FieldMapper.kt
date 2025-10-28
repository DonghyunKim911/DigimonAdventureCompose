package com.dis.data.mapper

import com.dis.core.domain.model.Field
import com.dis.data.model.FieldData

fun FieldData.toDomain(): Field = Field(
    field = field,
    id = id,
    image = image
)

fun Field.toData(): FieldData = FieldData(
    field = field,
    id = id,
    image = image
)
