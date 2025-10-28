package com.dis.data.model

data class DigimonData(
    val attribute: List<AttributeData?>,
    val description: List<DescriptionData?>,
    val field: List<FieldData?>,
    val id: Int,
    val image: List<ImageData?>,
    val level: List<LevelData?>,
    val name: String,
    val nextEvolution: List<NextEvolutionData?>,
    val priorEvolution: List<PriorEvolutionData?>,
    val releaseDate: String,
    val skills: List<SkillData?>,
    val type: List<TypeData?>,
    val xAntibody: Boolean,
)
