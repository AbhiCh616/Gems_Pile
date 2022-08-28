package com.example.gemspile.data.repository

import com.example.gemspile.data.data_mapper.toVideo
import com.example.gemspile.data.data_mapper.toVideoList
import com.example.gemspile.data.data_mapper.toVideoRow
import com.example.gemspile.local_source.dao.VideoDao
import com.example.gemspile.storage_interface.VideoRepository
import com.example.gemspile.validated_model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VideoRepositoryImpl(
    private val videoDao: VideoDao
): VideoRepository {
    override suspend fun getByUrl(url: String): Video? {
        val videoRow = videoDao.getByUrl(url = url)
        return videoRow?.toVideo()
    }

    override suspend fun delete(video: Video) {
        val videoRow = video.toVideoRow()
        videoDao.delete(video = videoRow)
    }

    override fun getAll(): Flow<List<Video>> =
        videoDao.getAll().map {
            it.toVideoList()
        }

    override suspend fun add(video: Video) {
        val videoRow = video.toVideoRow()
        videoDao.insert(video = videoRow)
    }

    override suspend fun update(video: Video) {
        val videoRow = video.toVideoRow()
        videoDao.update(video = videoRow)
    }
}