package com.core.presentation.mapper

import com.core.presentation.model.NextEvolutionModel
import com.dis.core.domain.model.NextEvolution

fun NextEvolution.toPresentation(): NextEvolutionModel = NextEvolutionModel(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)

fun NextEvolutionModel.toDomain(): NextEvolution = NextEvolution(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)
