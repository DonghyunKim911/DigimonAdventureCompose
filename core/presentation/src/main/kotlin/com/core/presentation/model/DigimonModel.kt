package com.core.presentation.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class DigimonModel(
    val attribute: List<AttributeModel?> = emptyList(),
    val description: List<DescriptionModel?> = emptyList(),
    val field: ImmutableList<FieldModel?> = persistentListOf(),
    val id: Int = -1,
    val image: ImmutableList<ImageModel?> = persistentListOf(),
    val level: List<LevelModel?> = emptyList(),
    val name: String = " ",
    val nextEvolution: List<NextEvolutionModel?> = emptyList(),
    val priorEvolution: List<PriorEvolutionModel?> = emptyList(),
    val releaseDate: String = "",
    val skills: ImmutableList<SkillModel?> = persistentListOf(),
    val type: List<TypeModel?> = emptyList(),
    val xAntibody: Boolean = false,
    val isFavorite: Boolean = false,
)
