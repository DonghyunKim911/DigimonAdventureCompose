package com.dis.local.digimon.mapper

import com.dis.core.database.entity.ContentEntity
import com.dis.data.model.ContentData

fun ContentEntity.toData(): ContentData = ContentData(
    href = href,
    id = id ?: 0,
    name = name,
    image = image
)

fun ContentData.toLocal(page: Int): ContentEntity = ContentEntity(
    href = href,
    id = id,
    name = name,
    image = image,
    page = page,
)
