package com.dis.local.digimon.mapper

import com.dis.core.database.entity.PriorEvolutionEntity
import com.dis.data.model.PriorEvolutionData

fun PriorEvolutionEntity.toData(): PriorEvolutionData =
    PriorEvolutionData(
        condition = condition,
        digimon = digimon,
        id = id,
        image = image,
        url = url,
    )

fun PriorEvolutionData.toLocal(): PriorEvolutionEntity =
    PriorEvolutionEntity(
        condition = condition,
        digimon = digimon,
        id = id,
        image = image,
        url = url,
    )
