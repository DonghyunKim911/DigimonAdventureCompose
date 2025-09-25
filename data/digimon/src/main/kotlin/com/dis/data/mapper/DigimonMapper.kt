package com.dis.data.mapper

import com.dis.data.model.AttributeData
import com.dis.data.model.DescriptionData
import com.dis.data.model.DigimonData
import com.dis.data.model.FieldData
import com.dis.data.model.ImageData
import com.dis.data.model.LevelData
import com.dis.data.model.NextEvolutionData
import com.dis.data.model.PriorEvolutionData
import com.dis.data.model.SkillData
import com.dis.data.model.TypeData
import com.dis.domain.digimon.model.Digimon

fun DigimonData.toDomain(): Digimon = Digimon(
    attribute = attribute?.map { it?.toDomain() },
    description = description?.map { it?.toDomain() },
    field = field?.map { it?.toDomain() },
    id = id,
    image = image?.map { it?.toDomain() },
    level = level?.map { it?.toDomain() },
    name = name,
    nextEvolution = nextEvolution?.map { it?.toDomain() },
    priorEvolution = priorEvolution?.map { it?.toDomain() },
    releaseDate = releaseDate,
    skills = skills?.map { it?.toDomain() },
    type = type?.map { it?.toDomain() },
    xAntibody = xAntibody
)

fun Digimon.toData(): DigimonData = DigimonData(
    attribute = attribute?.map {
        AttributeData(
            attribute = it?.attribute,
            id = it?.id
        )
    },
    description = description?.map {
        DescriptionData(
            description = it?.description,
            language = it?.language,
            origin = it?.origin
        )
    },
    field = field?.map {
        FieldData(
            field = it?.field,
            id = it?.id,
            image = it?.image
        )
    },
    id = id,
    image = image?.map {
        ImageData(
            href = it?.href,
            transparent = it?.transparent
        )
    },
    level = level?.map {
        LevelData(
            id = it?.id,
            level = it?.level
        )
    },
    name = name,
    nextEvolution = nextEvolution?.map {
        NextEvolutionData(
            condition = it?.condition,
            digimon = it?.digimon,
            id = it?.id,
            image = it?.image,
            url = it?.url
        )
    },
    priorEvolution = priorEvolution?.map {
        PriorEvolutionData(
            condition = it?.condition,
            digimon = it?.digimon,
            id = it?.id,
            image = it?.image,
            url = it?.url
        )
    },
    releaseDate = releaseDate,
    skills = skills?.map {
        SkillData(
            description = it?.description,
            id = it?.id,
            skill = it?.skill,
            translation = it?.translation
        )
    },
    type = type?.map {
        TypeData(
            id = it?.id,
            type = it?.type
        )
    },
    xAntibody = xAntibody,
)
