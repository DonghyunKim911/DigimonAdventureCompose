package com.dis.remote.digimon.mapper

import com.dis.data.model.NextEvolutionData
import com.dis.remote.digimon.model.NextEvolutionResponse

fun NextEvolutionResponse.toData(): NextEvolutionData =
    NextEvolutionData(
        condition = condition,
        digimon = digimon,
        id = id,
        image = image,
        url = url,
    )
