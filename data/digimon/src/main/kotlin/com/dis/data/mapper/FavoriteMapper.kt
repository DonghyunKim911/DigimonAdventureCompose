package com.dis.data.mapper

import com.dis.data.model.FavoriteData
import com.dis.domain.digimon.model.Favorite

fun FavoriteData.toDomain(): Favorite = Favorite(id = id)

fun Favorite.toData(): FavoriteData = FavoriteData(id = id)
