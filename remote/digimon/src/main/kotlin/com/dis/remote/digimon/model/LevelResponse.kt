package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class LevelResponse(
    @SerialName("id")
    val id: Int?,
    @SerialName("level")
    val level: String?
)
