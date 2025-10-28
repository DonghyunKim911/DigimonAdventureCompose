package com.core.presentation.mapper

import com.core.presentation.model.PriorEvolutionModel
import com.dis.core.domain.model.PriorEvolution

fun PriorEvolution.toPresentation(): PriorEvolutionModel = PriorEvolutionModel(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)


fun PriorEvolutionModel.toDomain(): PriorEvolution = PriorEvolution(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)
