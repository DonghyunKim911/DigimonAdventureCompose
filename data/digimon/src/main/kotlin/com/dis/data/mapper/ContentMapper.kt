package com.dis.data.mapper

import com.dis.data.model.ContentData
import com.dis.domain.digimon.model.Content

fun ContentData.toDomain(): Content = Content(
    href = href,
    id = id,
    name = name,
    image = image
)

fun Content.toData(): ContentData = ContentData(
    href = href,
    id = id,
    name = name,
    image = image
)
