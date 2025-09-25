package com.dis.local.digimon.mapper

import com.dis.core.database.entity.AttributeEntity
import com.dis.data.model.AttributeData

fun AttributeEntity.toData(): AttributeData = AttributeData(
    attribute = attribute,
    id = id
)

fun AttributeData.toLocal(): AttributeEntity = AttributeEntity(
    attribute = attribute,
    id = id
)
