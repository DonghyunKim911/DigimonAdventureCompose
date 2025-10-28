package com.dis.data.mapper

import com.dis.core.domain.model.Favorite
import com.dis.data.model.FavoriteData

fun FavoriteData.toDomain(): Favorite = Favorite(id = id)

fun Favorite.toData(): FavoriteData = FavoriteData(id = id)
