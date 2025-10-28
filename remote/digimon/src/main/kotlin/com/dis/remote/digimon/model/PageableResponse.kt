package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

/**
 * Used for getting Digimon list.
 * */
@Immutable
@Serializable
data class PageableResponse(
    @SerialName("currentPage")
    val currentPage: Int?,
    @SerialName("elementsOnPage")
    val elementsOnPage: Int?,
    @SerialName("nextPage")
    val nextPage: String?,
    @SerialName("previousPage")
    val previousPage: String?,
    @SerialName("totalElements")
    val totalElements: Int?,
    @SerialName("totalPages")
    val totalPages: Int?,
)
