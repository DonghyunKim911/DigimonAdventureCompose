package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Skill
import com.dis.presentation.model.SkillModel

fun Skill.toPresentation(): SkillModel = SkillModel(
    description = description,
    id = id,
    skill = skill,
    translation = translation
)

fun SkillModel.toDomain(): Skill = Skill(
    description = description,
    id = id,
    skill = skill,
    translation = translation
)
