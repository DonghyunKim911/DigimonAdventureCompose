package com.dis.data.mapper

import com.dis.data.model.SkillData
import com.dis.domain.digimon.model.Skill

fun SkillData.toDomain(): Skill = Skill(
    description = description,
    id = id,
    skill = skill,
    translation = translation
)

fun Skill.toData(): SkillData = SkillData(
    description = description,
    id = id,
    skill = skill,
    translation = translation
)
