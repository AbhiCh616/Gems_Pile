package com.example.gemspile.use_case

import com.example.gemspile.model_mapper.toValidatedVideo
import com.example.gemspile.raw_model.VideoRaw
import com.example.gemspile.storage_interface.VideoRepository
import com.example.gemspile.util.runSuspendCatching

class SaveVideo(
    private val videoRepository: VideoRepository
) {
    suspend operator fun invoke(video: VideoRaw) = runSuspendCatching {
        val validatedVideo = video.toValidatedVideo()
        videoRepository.add(video = validatedVideo)
    }
}