package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class FieldResponse(
    @SerialName("field")
    val `field`: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
)
