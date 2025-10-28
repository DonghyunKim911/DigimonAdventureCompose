package com.dis.local.digimon.mapper

import com.dis.core.database.entity.AttributeEntity
import com.dis.core.database.entity.DescriptionEntity
import com.dis.core.database.entity.DigimonEntity
import com.dis.core.database.entity.FieldEntity
import com.dis.core.database.entity.ImageEntity
import com.dis.core.database.entity.LevelEntity
import com.dis.core.database.entity.NextEvolutionEntity
import com.dis.core.database.entity.PriorEvolutionEntity
import com.dis.core.database.entity.SkillEntity
import com.dis.core.database.entity.TypeEntity
import com.dis.data.model.DigimonData

fun DigimonEntity.toData(): DigimonData =
    DigimonData(
        attribute = attribute.map { it?.toData() },
        description = description.map { it?.toData() },
        field = field.map { it?.toData() },
        id = id,
        image = image.map { it?.toData() },
        level = level.map { it?.toData() },
        name = name,
        nextEvolution = nextEvolution.map { it?.toData() },
        priorEvolution = priorEvolution.map { it?.toData() },
        releaseDate = releaseDate,
        skills = skills.map { it?.toData() },
        type = type.map { it?.toData() },
        xAntibody = xAntibody,
    )

fun DigimonData.toLocal(): DigimonEntity =
    DigimonEntity(
        attribute =
            attribute.map {
                AttributeEntity(
                    attribute = it?.attribute,
                    id = it?.id,
                )
            },
        description =
            description.map {
                DescriptionEntity(
                    description = it?.description,
                    language = it?.language,
                    origin = it?.origin,
                )
            },
        field =
            field.map {
                FieldEntity(
                    field = it?.field,
                    id = it?.id,
                    image = it?.image,
                )
            },
        id = id,
        image =
            image.map {
                ImageEntity(
                    href = it?.href,
                    transparent = it?.transparent,
                )
            },
        level =
            level.map {
                LevelEntity(
                    id = it?.id,
                    level = it?.level,
                )
            },
        name = name,
        nextEvolution =
            nextEvolution.map {
                NextEvolutionEntity(
                    condition = it?.condition,
                    digimon = it?.digimon,
                    id = it?.id,
                    image = it?.image,
                    url = it?.url,
                )
            },
        priorEvolution =
            priorEvolution.map {
                PriorEvolutionEntity(
                    condition = it?.condition,
                    digimon = it?.digimon,
                    id = it?.id,
                    image = it?.image,
                    url = it?.url,
                )
            },
        releaseDate = releaseDate,
        skills =
            skills.map {
                SkillEntity(
                    description = it?.description,
                    id = it?.id,
                    skill = it?.skill,
                    translation = it?.translation,
                )
            },
        type =
            type.map {
                TypeEntity(
                    id = it?.id,
                    type = it?.type,
                )
            },
        xAntibody = xAntibody,
    )
