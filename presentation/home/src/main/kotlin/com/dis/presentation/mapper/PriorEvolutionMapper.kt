package com.dis.presentation.mapper

import com.dis.domain.digimon.model.PriorEvolution
import com.dis.presentation.model.PriorEvolutionModel

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
