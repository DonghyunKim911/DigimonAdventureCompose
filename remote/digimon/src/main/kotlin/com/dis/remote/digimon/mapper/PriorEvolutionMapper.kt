package com.dis.remote.digimon.mapper

import com.dis.data.model.PriorEvolutionData
import com.dis.remote.digimon.model.PriorEvolutionResponse

fun PriorEvolutionResponse.toData(): PriorEvolutionData = PriorEvolutionData(
    condition = condition,
    digimon = digimon,
    id = id,
    image = image,
    url = url
)
