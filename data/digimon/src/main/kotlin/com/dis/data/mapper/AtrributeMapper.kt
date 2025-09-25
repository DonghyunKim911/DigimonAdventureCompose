package com.dis.data.mapper

import com.dis.data.model.AttributeData
import com.dis.domain.digimon.model.Attribute

fun AttributeData.toDomain(): Attribute = Attribute(
    attribute = attribute,
    id = id
)

fun Attribute.toData(): AttributeData = AttributeData(
    attribute = attribute,
    id = id
)
