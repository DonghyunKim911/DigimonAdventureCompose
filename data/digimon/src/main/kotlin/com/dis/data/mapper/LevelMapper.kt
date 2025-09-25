package com.dis.data.mapper

import com.dis.data.model.LevelData
import com.dis.domain.digimon.model.Level

fun LevelData.toDomain(): Level = Level(
    id = id,
    level = level
)

fun Level.toData(): LevelData = LevelData(
    id = id,
    level = level
)
