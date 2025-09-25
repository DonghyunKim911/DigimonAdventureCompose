package com.dis.remote.digimon.mapper

import com.dis.data.model.DescriptionData
import com.dis.remote.digimon.model.DescriptionResponse

fun DescriptionResponse.toData(): DescriptionData = DescriptionData(
    description = description,
    language = language,
    origin = origin
)
