package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class ImageResponse(
    @SerialName("href")
    val href: String?,
    @SerialName("transparent")
    val transparent: Boolean?
)
