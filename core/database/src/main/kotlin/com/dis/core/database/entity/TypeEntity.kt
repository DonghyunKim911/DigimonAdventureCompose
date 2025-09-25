package com.dis.core.database.entity

import kotlinx.serialization.Serializable

@Serializable
data class TypeEntity(
    val id: Int?,
    val type: String?
)
