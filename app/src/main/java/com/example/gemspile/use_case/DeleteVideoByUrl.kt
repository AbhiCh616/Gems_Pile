package com.example.gemspile.use_case

import com.example.gemspile.storage_interface.VideoRepository
import com.example.gemspile.util.runSuspendCatching
import javax.inject.Inject

class DeleteVideoByUrl @Inject constructor(
    private val videoRepository: VideoRepository
) {
    suspend operator fun invoke(url: String) = runSuspendCatching {
        videoRepository.deleteByUrl(url = url)
    }
}