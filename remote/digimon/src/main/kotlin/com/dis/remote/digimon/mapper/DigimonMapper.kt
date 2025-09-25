package com.dis.remote.digimon.mapper

import com.dis.data.model.DigimonData
import com.dis.remote.digimon.model.DigimonResponse

fun DigimonResponse.toData(): DigimonData = DigimonData(
    attribute = attribute?.map { it?.toData() },
    description = description?.map { it?.toData() },
    field = field?.map { it?.toData() },
    id = id,
    image = image?.map { it?.toData() },
    level = level?.map { it?.toData() },
    name = name,
    nextEvolution = nextEvolution?.map { it?.toData() },
    priorEvolution = priorEvolution?.map { it?.toData() },
    releaseDate = releaseDate,
    skills = skills?.map { it?.toData() },
    type = type?.map { it?.toData() },
    xAntibody = xAntibody
)
