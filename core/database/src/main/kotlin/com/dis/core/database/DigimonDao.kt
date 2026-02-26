package com.dis.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.dis.core.database.entity.ContentEntity
import com.dis.core.database.entity.DigimonEntity
import com.dis.core.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DigimonDao {
    @Upsert
    suspend fun upsertDigimonList(contents: List<ContentEntity>)

    @Query("SELECT * FROM ContentEntity WHERE page = :page")
    suspend fun getDigimonList(page: Int): List<ContentEntity>

    @Query("SELECT * FROM ContentEntity WHERE id = :id")
    suspend fun getDigimonContent(id: Int): ContentEntity?

    @Query("SELECT * FROM DigimonEntity WHERE id = :id")
    suspend fun getDigimon(id: Int): DigimonEntity?

    @Upsert
    suspend fun upsertDigimon(digimon: DigimonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavoriteDigimon(favorite: FavoriteEntity)

    @Query("SELECT * FROM FavoriteEntity ORDER BY id DESC")
    fun getFavoriteDigimonList(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM FavoriteEntity WHERE id = :id")
    suspend fun getFavorite(id: Int): FavoriteEntity? // todo : use `distinctUntilChanged`.
}
