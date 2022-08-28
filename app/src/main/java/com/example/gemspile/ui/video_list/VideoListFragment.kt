package com.example.gemspile.ui.video_list

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gemspile.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoListFragment : Fragment(R.layout.video_list) {
    private val viewModel: VideoListViewModel by viewModels()
}