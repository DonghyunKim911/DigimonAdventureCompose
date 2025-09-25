package com.dis.presentation.mapper

import com.dis.domain.digimon.model.NextEvolution
import com.dis.presentation.model.NextEvolutionModel

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
