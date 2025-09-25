package com.dis.core.database.entity

import kotlinx.serialization.Serializable


/**
 * Used for getting Digimon list from Room.
 * */

@Serializable
data class DigimonListEntity(
    val contents: List<ContentEntity?>?,
    val pageable: PageableEntity? // Not used in this project.
)
