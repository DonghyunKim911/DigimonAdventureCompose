package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Level
import com.dis.presentation.model.LevelModel

fun Level.toPresentation(): LevelModel = LevelModel(
    id = id,
    level = level
)

fun LevelModel.toDomain(): Level = Level(
    id = id,
    level = level
)
