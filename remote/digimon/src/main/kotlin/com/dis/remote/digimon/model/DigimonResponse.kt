package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class DigimonResponse(
    @SerialName("attributes")
    val attribute: List<AttributeResponse?> = emptyList(),
    @SerialName("descriptions")
    val description: List<DescriptionResponse?> = emptyList(),
    @SerialName("fields")
    val field: List<FieldResponse?> = emptyList(),
    @SerialName("id")
    val id: Int = -1,
    @SerialName("images")
    val image: List<ImageResponse?> = emptyList(),
    @SerialName("levels")
    val level: List<LevelResponse?> = emptyList(),
    @SerialName("name")
    val name: String = "",
    @SerialName("nextEvolutions")
    val nextEvolution: List<NextEvolutionResponse?> = emptyList(),
    @SerialName("priorEvolutions")
    val priorEvolution: List<PriorEvolutionResponse?> = emptyList(),
    @SerialName("releaseDate")
    val releaseDate: String = "",
    @SerialName("skills")
    val skills: List<SkillResponse?> = emptyList(),
    @SerialName("types")
    val type: List<TypeResponse?> = emptyList(),
    @SerialName("xAntibody")
    val xAntibody: Boolean = false,
)
