package com.example.gemspile.ui.video_list

import androidx.lifecycle.ViewModel
import com.example.gemspile.use_case.GetAllVideos
import com.example.gemspile.validated_model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val getAllVideos: GetAllVideos
) : ViewModel() {
    var videos: MutableList<Video>? = null

    suspend fun fetchVideos() {
        videos = getAllVideos().toMutableList()
    }

    private var selectedVideos = mutableListOf<Video>()

    fun selectVideo(video: Video) {
        selectedVideos.add(video)
    }

    fun deselectVideo(video: Video) {
        selectedVideos.remove(video)
    }

    fun isAnyVideoSelected(): Boolean {
        return selectedVideos.isNotEmpty()
    }
}