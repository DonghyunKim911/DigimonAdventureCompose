package com.core.presentation.mapper

import com.core.presentation.model.AttributeModel
import com.dis.core.domain.model.Attribute

fun Attribute.toPresentation(): AttributeModel = AttributeModel(
    attribute = attribute,
    id = id
)
