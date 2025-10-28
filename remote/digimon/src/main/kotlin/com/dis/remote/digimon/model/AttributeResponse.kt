package com.dis.remote.digimon.model

import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class AttributeResponse(
    val attribute: String?,
    val id: Int?,
)
