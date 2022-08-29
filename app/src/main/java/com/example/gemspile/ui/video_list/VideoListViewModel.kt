package com.example.gemspile.ui.video_list

import androidx.lifecycle.ViewModel
import com.example.gemspile.use_case.ObserveVideos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    observeVideos: ObserveVideos
) : ViewModel() {

    private val _selectedVideos = MutableStateFlow(listOf<String>())

    val videoItems = observeVideos().combine(_selectedVideos) { videos, selectedVideos ->
        val mergedList = mutableListOf<VideoItem>()
        videos.forEach { video ->
            mergedList.add(
                VideoItem(
                    url = video.url,
                    isSelected = selectedVideos.contains(video.url)
                )
            )
        }
        mergedList.toList()
    }

    private fun selectVideo(videoItem: VideoItem) {
        _selectedVideos.update { oldSelection ->
            oldSelection + videoItem.url
        }
    }

    private fun deselectVideo(videoItem: VideoItem) {
        _selectedVideos.update { oldSelection ->
            oldSelection - videoItem.url
        }
    }

    fun onCardLongClick(videoItem: VideoItem) {
        if (videoItem.isSelected) {
            deselectVideo(videoItem = videoItem)
        } else {
            selectVideo(videoItem = videoItem)
        }
    }

    fun onCardClick(videoItem: VideoItem) {
        if (videoItem.isSelected) {
            deselectVideo(videoItem = videoItem)
        } else if (areVideosSelected()) {
            selectVideo(videoItem = videoItem)
        }
    }

    fun areVideosSelected(): Boolean =
        _selectedVideos.value.isNotEmpty()

}