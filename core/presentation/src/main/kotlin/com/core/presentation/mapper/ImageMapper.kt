package com.core.presentation.mapper

import com.core.presentation.model.ImageModel
import com.dis.core.domain.model.Image

fun Image.toPresentation(): ImageModel =
    ImageModel(
        href = href,
        transparent = transparent,
    )

fun ImageModel.toDomain(): Image =
    Image(
        href = href,
        transparent = transparent,
    )
