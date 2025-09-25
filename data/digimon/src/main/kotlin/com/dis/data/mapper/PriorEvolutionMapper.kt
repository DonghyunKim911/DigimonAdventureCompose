package com.dis.data.mapper

import com.dis.data.model.PriorEvolutionData
import com.dis.domain.digimon.model.PriorEvolution

fun PriorEvolutionData.toDomain(): PriorEvolution = PriorEvolution(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)


fun PriorEvolution.toData(): PriorEvolutionData = PriorEvolutionData(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)
