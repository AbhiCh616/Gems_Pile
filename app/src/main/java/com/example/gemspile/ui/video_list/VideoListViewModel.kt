package com.example.gemspile.ui.video_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gemspile.use_case.DeleteVideoByUrl
import com.example.gemspile.use_case.ObserveVideos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    observeVideos: ObserveVideos,
    private val deleteVideoByUrl: DeleteVideoByUrl
) : ViewModel() {

    private val _selectedVideosUrl = MutableStateFlow(listOf<String>())

    val videoItems = observeVideos().combine(_selectedVideosUrl) { videos, selectedVideos ->
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
        _selectedVideosUrl.update { oldSelection ->
            oldSelection + videoItem.url
        }
    }

    private fun deselectVideo(videoItem: VideoItem) {
        _selectedVideosUrl.update { oldSelection ->
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
        _selectedVideosUrl.value.isNotEmpty()

    fun observeAreVideosSelected(): Flow<Boolean> =
        _selectedVideosUrl.map {
            it.isNotEmpty()
        }

    fun deselectAllVideos() {
        _selectedVideosUrl.value = listOf()
    }

    fun deleteSelectedVideos() = viewModelScope.launch {
        for (videoUrl in _selectedVideosUrl.value) {
            deleteVideoByUrl(url = videoUrl)
        }
        deselectAllVideos()
    }
}