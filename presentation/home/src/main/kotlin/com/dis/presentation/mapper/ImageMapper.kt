package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Image
import com.dis.presentation.model.ImageModel

fun Image.toPresentation(): ImageModel = ImageModel(
    href = href,
    transparent = transparent
)

fun ImageModel.toDomain(): Image = Image(
    href = href,
    transparent = transparent
)
