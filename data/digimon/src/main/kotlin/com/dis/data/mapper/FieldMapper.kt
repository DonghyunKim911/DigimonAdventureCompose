package com.dis.data.mapper

import com.dis.data.model.FieldData
import com.dis.domain.digimon.model.Field

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
