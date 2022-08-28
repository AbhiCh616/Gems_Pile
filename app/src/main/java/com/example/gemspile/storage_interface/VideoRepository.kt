package com.example.gemspile.storage_interface

import com.example.gemspile.validated_model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun getByUrl(url: String) : Video?
    suspend fun delete(video: Video)
    fun observeAll() : Flow<List<Video>>
    suspend fun getAll(): List<Video>
    suspend fun add(video: Video)
    suspend fun update(video: Video)
}