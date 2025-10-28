package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class NextEvolutionResponse(
    @SerialName("condition")
    val condition: String?,
    @SerialName("digimon")
    val digimon: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("url")
    val url: String?,
)
