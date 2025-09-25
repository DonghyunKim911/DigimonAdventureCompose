package com.dis.core.database.entity

import kotlinx.serialization.Serializable

@Serializable
data class DescriptionEntity(
    val description: String?,
    val language: String?,
    val origin: String?
)
