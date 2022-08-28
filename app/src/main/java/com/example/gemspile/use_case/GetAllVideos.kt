package com.example.gemspile.use_case

import com.example.gemspile.storage_interface.VideoRepository

class GetAllVideos(
    private val videoRepository: VideoRepository
) {
   operator fun invoke() = videoRepository.getAll()
}