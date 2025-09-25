package com.dis.domain.digimon.model

/**
 * Used for getting Digimon list.
 * */
data class DigimonList(
    val content: List<Content?>?,
    val pageable: Pageable? // Not used in this project.
)