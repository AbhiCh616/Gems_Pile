package com.example.gemspile.use_case

import com.example.gemspile.storage_interface.VideoRepository
import javax.inject.Inject

class GetAllVideos @Inject constructor(
    private val videoRepository: VideoRepository
) {
   suspend operator fun invoke() = videoRepository.getAll()
}