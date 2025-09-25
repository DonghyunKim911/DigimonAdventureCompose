package com.dis.local.digimon.mapper

import com.dis.core.database.entity.FieldEntity
import com.dis.data.model.FieldData

fun FieldEntity.toData(): FieldData = FieldData(
    field = field,
    id = id,
    image = image
)

fun FieldData.toLocal(): FieldEntity = FieldEntity(
    field = field,
    id = id,
    image = image
)
