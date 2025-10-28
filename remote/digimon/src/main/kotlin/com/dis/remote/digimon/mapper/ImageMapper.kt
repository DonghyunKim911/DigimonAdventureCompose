package com.dis.remote.digimon.mapper

import com.dis.data.model.ImageData
import com.dis.remote.digimon.model.ImageResponse

fun ImageResponse.toData(): ImageData =
    ImageData(
        href = href,
        transparent = transparent,
    )
