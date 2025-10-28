package com.core.presentation.mapper

import com.core.presentation.model.SkillModel
import com.dis.core.domain.model.Skill

fun Skill.toPresentation(): SkillModel =
    SkillModel(
        description = description,
        id = id,
        skill = skill,
        translation = translation,
    )

fun SkillModel.toDomain(): Skill =
    Skill(
        description = description,
        id = id,
        skill = skill,
        translation = translation,
    )
