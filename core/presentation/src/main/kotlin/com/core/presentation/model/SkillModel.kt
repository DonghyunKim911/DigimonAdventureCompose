package com.core.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class SkillModel(
    val description: String?,
    val id: Int?,
    val skill: String?,
    val translation: String?
)
