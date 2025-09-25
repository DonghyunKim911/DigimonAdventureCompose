package com.dis.remote.digimon.mapper

import com.dis.data.model.ContentData
import com.dis.remote.digimon.model.ContentResponse

fun ContentResponse.toData(): ContentData = ContentData(
    href = href,
    id = id ?: 0,
    name = name,
    image = image
)
