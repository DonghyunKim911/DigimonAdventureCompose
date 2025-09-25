package com.dis.local.digimon.mapper

import com.dis.core.database.entity.SkillEntity
import com.dis.data.model.SkillData

fun SkillEntity.toData(): SkillData = SkillData(
    description = description,
    id = id,
    skill = skill,
    translation = translation
)

fun SkillData.toLocal(): SkillEntity = SkillEntity(
    description = description,
    id = id,
    skill = skill,
    translation = translation
)
