package com.example.gemspile.local_source.dao

import androidx.room.*
import com.example.gemspile.local_source.table.VideoRow
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {

    @Query("SELECT * FROM video WHERE url = :url")
    suspend fun getByUrl(url: String): VideoRow?

    @Query("DELETE FROM video WHERE url = :url")
    suspend fun deleteByUrl(url: String)

    @Delete
    suspend fun delete(video: VideoRow)

    @Query("SELECT * FROM video")
    fun observeAll(): Flow<List<VideoRow>>

    @Query("SELECT * FROM video")
    suspend fun getAll(): List<VideoRow>

    @Insert
    suspend fun insert(video: VideoRow)

    @Update
    suspend fun update(video: VideoRow)
}