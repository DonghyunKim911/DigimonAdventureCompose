package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class SkillResponse(
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("skill")
    val skill: String?,
    @SerialName("translation")
    val translation: String?,
)
