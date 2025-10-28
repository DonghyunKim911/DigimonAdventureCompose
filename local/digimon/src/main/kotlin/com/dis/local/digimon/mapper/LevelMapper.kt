package com.dis.local.digimon.mapper

import com.dis.core.database.entity.LevelEntity
import com.dis.data.model.LevelData

fun LevelEntity.toData(): LevelData =
    LevelData(
        id = id,
        level = level,
    )

fun LevelData.toLocal(): LevelEntity =
    LevelEntity(
        id = id,
        level = level,
    )
