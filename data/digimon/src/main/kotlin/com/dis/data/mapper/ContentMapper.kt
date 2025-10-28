package com.dis.data.mapper

import com.dis.core.domain.model.Content
import com.dis.data.model.ContentData

fun ContentData.toDomain(): Content =
    Content(
        href = href,
        id = id,
        name = name,
        image = image,
    )

fun Content.toData(): ContentData =
    ContentData(
        href = href,
        id = id,
        name = name,
        image = image,
    )
