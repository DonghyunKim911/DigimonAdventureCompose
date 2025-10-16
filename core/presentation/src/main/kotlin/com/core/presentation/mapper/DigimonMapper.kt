package com.core.presentation.mapper

import com.core.presentation.model.DigimonModel
import com.dis.core.domain.model.Attribute
import com.dis.core.domain.model.Description
import com.dis.core.domain.model.Digimon
import com.dis.core.domain.model.Field
import com.dis.core.domain.model.Image
import com.dis.core.domain.model.Level
import com.dis.core.domain.model.NextEvolution
import com.dis.core.domain.model.PriorEvolution
import com.dis.core.domain.model.Skill
import com.dis.core.domain.model.Type
import kotlinx.collections.immutable.toImmutableList

fun Digimon.toPresentation(): DigimonModel = DigimonModel(
    attribute = attribute.map { it?.toPresentation() },
    description = description.map { it?.toPresentation() },
    field = field.map { it?.toPresentation() }.toImmutableList(),
    id = id,
    image = image.map { it?.toPresentation() }.toImmutableList(),
    level = level.map { it?.toPresentation() },
    name = name,
    nextEvolution = nextEvolution.map { it?.toPresentation() },
    priorEvolution = priorEvolution.map { it?.toPresentation() },
    releaseDate = releaseDate,
    skills = skills.map { it?.toPresentation() }.toImmutableList(),
    type = type.map { it?.toPresentation() },
    xAntibody = xAntibody,
    isFavorite = isFavorite,
)

fun DigimonModel.toDomain(): Digimon = Digimon(
    attribute = attribute.map {
        Attribute(
            attribute = it?.attribute,
            id = it?.id
        )
    },
    description = description.map {
        Description(
            description = it?.description,
            language = it?.language,
            origin = it?.origin
        )
    },
    field = field.map {
        Field(
            field = it?.field,
            id = it?.id,
            image = it?.image
        )
    },
    id = id,
    image = image.map {
        Image(
            href = it?.href,
            transparent = it?.transparent
        )
    },
    level = level.map {
        Level(
            id = it?.id,
            level = it?.level
        )
    },
    name = name,
    nextEvolution = nextEvolution.map {
        NextEvolution(
            condition = it?.condition,
            digimon = it?.digimon,
            id = it?.id,
            image = it?.image,
            url = it?.url
        )
    },
    priorEvolution = priorEvolution.map {
        PriorEvolution(
            condition = it?.condition,
            digimon = it?.digimon,
            id = it?.id,
            image = it?.image,
            url = it?.url
        )
    },
    releaseDate = releaseDate,
    skills = skills.map {
        Skill(
            description = it?.description,
            id = it?.id,
            skill = it?.skill,
            translation = it?.translation
        )
    },
    type = type.map {
        Type(
            id = it?.id,
            type = it?.type
        )
    },
    xAntibody = xAntibody,
)
