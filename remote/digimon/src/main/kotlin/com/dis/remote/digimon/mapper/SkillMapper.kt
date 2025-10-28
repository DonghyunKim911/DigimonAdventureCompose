package com.dis.remote.digimon.mapper

import com.dis.data.model.SkillData
import com.dis.remote.digimon.model.SkillResponse

fun SkillResponse.toData(): SkillData =
    SkillData(
        description = description,
        id = id,
        skill = skill,
        translation = translation,
    )
