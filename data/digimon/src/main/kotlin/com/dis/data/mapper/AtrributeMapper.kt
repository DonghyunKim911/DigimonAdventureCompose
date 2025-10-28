package com.dis.data.mapper

import com.dis.core.domain.model.Attribute
import com.dis.data.model.AttributeData

fun AttributeData.toDomain(): Attribute = Attribute(
    attribute = attribute,
    id = id
)

fun Attribute.toData(): AttributeData = AttributeData(
    attribute = attribute,
    id = id
)
