package com.core.presentation.mapper

import com.core.presentation.model.ContentModel
import com.dis.core.domain.model.Content

fun Content.toPresentation() =
    ContentModel(
        href = href,
        id = id,
        name = name,
        image = image,
    )
