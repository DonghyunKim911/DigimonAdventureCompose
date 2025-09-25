package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class TypeResponse(
    @SerialName("id")
    val id: Int?,
    @SerialName("type")
    val type: String?
)
