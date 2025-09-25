package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Content
import com.dis.presentation.model.ContentModel

fun Content.toPresentation() = ContentModel(
    href = href,
    id = id,
    name = name,
    image = image,
)
