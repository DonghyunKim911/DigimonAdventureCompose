package com.core.presentation.mapper

import com.core.presentation.model.DescriptionModel
import com.dis.core.domain.model.Description

fun Description.toPresentation(): DescriptionModel = DescriptionModel(
    description = description,
    language = language,
    origin = origin
)
