package com.dis.local.digimon.mapper

import com.dis.core.database.entity.NextEvolutionEntity
import com.dis.data.model.NextEvolutionData

fun NextEvolutionEntity.toData(): NextEvolutionData = NextEvolutionData(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)

fun NextEvolutionData.toLocal(): NextEvolutionEntity = NextEvolutionEntity(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)
