package com.dis.remote.digimon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class DigimonResponse(
    @SerialName("attributes")
    val attribute: List<AttributeResponse?>?,
    @SerialName("descriptions")
    val description: List<DescriptionResponse?>?,
    @SerialName("fields")
    val field: List<FieldResponse?>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("images")
    val image: List<ImageResponse?>?,
    @SerialName("levels")
    val level: List<LevelResponse?>?,
    @SerialName("name")
    val name: String?,
    @SerialName("nextEvolutions")
    val nextEvolution: List<NextEvolutionResponse?>?,
    @SerialName("priorEvolutions")
    val priorEvolution: List<PriorEvolutionResponse?>?,
    @SerialName("releaseDate")
    val releaseDate: String?,
    @SerialName("skills")
    val skills: List<SkillResponse?>?,
    @SerialName("types")
    val type: List<TypeResponse?>?,
    @SerialName("xAntibody")
    val xAntibody: Boolean?
)
