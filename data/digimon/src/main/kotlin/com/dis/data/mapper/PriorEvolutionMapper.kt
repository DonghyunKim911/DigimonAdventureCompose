package com.dis.data.mapper

import com.dis.core.domain.model.PriorEvolution
import com.dis.data.model.PriorEvolutionData

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
