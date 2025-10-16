package com.dis.data.mapper

import com.dis.core.domain.model.NextEvolution
import com.dis.data.model.NextEvolutionData

fun NextEvolutionData.toDomain(): NextEvolution = NextEvolution(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)

fun NextEvolution.toData(): NextEvolutionData = NextEvolutionData(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)
