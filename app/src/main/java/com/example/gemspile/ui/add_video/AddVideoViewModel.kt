package com.example.gemspile.ui.add_video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gemspile.raw_model.VideoRaw
import com.example.gemspile.use_case.SaveVideo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddVideoViewModel @Inject constructor(
    private val saveVideo: SaveVideo
) : ViewModel() {
    var urlTextField = ""

    fun onSaveClick() = viewModelScope.launch {
        val videoRaw = VideoRaw(
            url = urlTextField
        )
        saveVideo(video = videoRaw)
    }
}