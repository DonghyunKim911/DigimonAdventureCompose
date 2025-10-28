package com.dis.remote.digimon.mapper

import com.dis.data.model.TypeData
import com.dis.remote.digimon.model.TypeResponse

fun TypeResponse.toData(): TypeData =
    TypeData(
        id = id,
        type = type,
    )
