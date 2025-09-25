package com.dis.data.mapper

import com.dis.data.model.ImageData
import com.dis.domain.digimon.model.Image

fun ImageData.toDomain(): Image = Image(
    href = href,
    transparent = transparent
)

fun Image.toData(): ImageData = ImageData(
    href = href,
    transparent = transparent
)
