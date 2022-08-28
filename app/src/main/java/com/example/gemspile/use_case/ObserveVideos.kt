package com.example.gemspile.use_case

import com.example.gemspile.storage_interface.VideoRepository
import javax.inject.Inject

class ObserveVideos @Inject constructor(
    private val videoRepository: VideoRepository
) {
    operator fun invoke() = videoRepository.observeAll()
}