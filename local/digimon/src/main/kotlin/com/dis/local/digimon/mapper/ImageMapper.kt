package com.dis.local.digimon.mapper

import com.dis.core.database.entity.ImageEntity
import com.dis.data.model.ImageData

fun ImageEntity.toData(): ImageData =
    ImageData(
        href = href,
        transparent = transparent,
    )

fun ImageData.toLocal(): ImageEntity =
    ImageEntity(
        href = href,
        transparent = transparent,
    )
