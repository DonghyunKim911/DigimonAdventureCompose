package com.dis.data.mapper

import com.dis.core.domain.model.Level
import com.dis.data.model.LevelData

fun LevelData.toDomain(): Level = Level(
    id = id,
    level = level
)

fun Level.toData(): LevelData = LevelData(
    id = id,
    level = level
)
