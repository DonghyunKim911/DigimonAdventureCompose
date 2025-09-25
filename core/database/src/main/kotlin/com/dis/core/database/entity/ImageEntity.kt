package com.dis.core.database.entity

import kotlinx.serialization.Serializable

@Serializable
data class ImageEntity(
    val href: String?,
    val transparent: Boolean?
)
