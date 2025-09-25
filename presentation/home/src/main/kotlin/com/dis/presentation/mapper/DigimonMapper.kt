package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Attribute
import com.dis.domain.digimon.model.Description
import com.dis.domain.digimon.model.Digimon
import com.dis.domain.digimon.model.Field
import com.dis.domain.digimon.model.Image
import com.dis.domain.digimon.model.Level
import com.dis.domain.digimon.model.NextEvolution
import com.dis.domain.digimon.model.PriorEvolution
import com.dis.domain.digimon.model.Skill
import com.dis.domain.digimon.model.Type
import com.dis.presentation.model.DigimonModel
import kotlin.collections.map


fun Digimon.toPresentation(): DigimonModel = DigimonModel(
    attribute = attribute?.map { it?.toPresentation() },
    description = description?.map { it?.toPresentation() },
    field = field?.map { it?.toPresentation() },
    id = id,
    image = image?.map { it?.toPresentation() },
    level = level?.map { it?.toPresentation() },
    name = name,
    nextEvolution = nextEvolution?.map { it?.toPresentation() },
    priorEvolution = priorEvolution?.map { it?.toPresentation() },
    releaseDate = releaseDate,
    skills = skills?.map { it?.toPresentation() },
    type = type?.map { it?.toPresentation() },
    xAntibody = xAntibody
)

fun DigimonModel.toDomain(): Digimon = Digimon(
    attribute = attribute?.map {
        Attribute(
            attribute = it?.attribute,
            id = it?.id
        )
    },
    description = description?.map {
        Description(
            description = it?.description,
            language = it?.language,
            origin = it?.origin
        )
    },
    field = field?.map {
        Field(
            field = it?.field,
            id = it?.id,
            image = it?.image
        )
    },
    id = id,
    image = image?.map {
        Image(
            href = it?.href,
            transparent = it?.transparent
        )
    },
    level = level?.map {
        Level(
            id = it?.id,
            level = it?.level
        )
    },
    name = name,
    nextEvolution = nextEvolution?.map {
        NextEvolution(
            condition = it?.condition,
            digimon = it?.digimon,
            id = it?.id,
            image = it?.image,
            url = it?.url
        )
    },
    priorEvolution = priorEvolution?.map {
        PriorEvolution(
            condition = it?.condition,
            digimon = it?.digimon,
            id = it?.id,
            image = it?.image,
            url = it?.url
        )
    },
    releaseDate = releaseDate,
    skills = skills?.map {
        Skill(
            description = it?.description,
            id = it?.id,
            skill = it?.skill,
            translation = it?.translation
        )
    },
    type = type?.map {
        Type(
            id = it?.id,
            type = it?.type
        )
    },
    xAntibody = xAntibody,
)
