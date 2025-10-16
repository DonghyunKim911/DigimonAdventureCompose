package com.dis.data.mapper

import com.dis.core.domain.model.Image
import com.dis.data.model.ImageData

fun ImageData.toDomain(): Image = Image(
    href = href,
    transparent = transparent
)

fun Image.toData(): ImageData = ImageData(
    href = href,
    transparent = transparent
)
