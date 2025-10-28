package com.dis.data.mapper

import com.dis.core.domain.model.Skill
import com.dis.data.model.SkillData

fun SkillData.toDomain(): Skill =
    Skill(
        description = description,
        id = id,
        skill = skill,
        translation = translation,
    )

fun Skill.toData(): SkillData =
    SkillData(
        description = description,
        id = id,
        skill = skill,
        translation = translation,
    )
