package com.core.presentation.mapper

import com.core.presentation.model.LevelModel
import com.dis.core.domain.model.Level

fun Level.toPresentation(): LevelModel = LevelModel(
    id = id,
    level = level
)

fun LevelModel.toDomain(): Level = Level(
    id = id,
    level = level
)
