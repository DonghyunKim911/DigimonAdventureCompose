package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Description
import com.dis.presentation.model.DescriptionModel

fun Description.toPresentation(): DescriptionModel = DescriptionModel(
    description = description,
    language = language,
    origin = origin
)
