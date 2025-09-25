package com.dis.presentation.model

data class DigimonModel(
    val attribute: List<AttributeModel?>?,
    val description: List<DescriptionModel?>?,
    val field: List<FieldModel?>?,
    val id: Int?,
    val image: List<ImageModel?>?,
    val level: List<LevelModel?>?,
    val name: String?,
    val nextEvolution: List<NextEvolutionModel?>?,
    val priorEvolution: List<PriorEvolutionModel?>?,
    val releaseDate: String?,
    val skills: List<SkillModel?>?,
    val type: List<TypeModel?>?,
    val xAntibody: Boolean?
)
