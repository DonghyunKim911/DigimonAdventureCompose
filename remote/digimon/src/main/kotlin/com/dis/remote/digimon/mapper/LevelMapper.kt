package com.dis.remote.digimon.mapper

import com.dis.data.model.LevelData
import com.dis.remote.digimon.model.LevelResponse

fun LevelResponse.toData(): LevelData =
    LevelData(
        id = id,
        level = level,
    )
