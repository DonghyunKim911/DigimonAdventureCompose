package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

/**
 * Used for getting Digimon list.
 * */

@Immutable
@Serializable
data class ContentResponse(
    @SerialName("href")
    val href: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("image")
    val image: String?,
)
