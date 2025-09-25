package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Attribute
import com.dis.presentation.model.AttributeModel

fun Attribute.toPresentation(): AttributeModel = AttributeModel(
    attribute = attribute,
    id = id
)
