package com.example.gemspile.ui.video_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gemspile.R
import com.example.gemspile.databinding.VideoListBinding
import com.example.gemspile.validated_model.Video
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideoListFragment : Fragment() {
    private val viewModel: VideoListViewModel by viewModels()
    private lateinit var binding: VideoListBinding
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VideoListBinding.inflate(inflater, container, false)

        uiScope.launch {
            if (viewModel.videos == null) {
                viewModel.fetchVideos()
            }
            binding.rvVideos.let {
                it.layoutManager = LinearLayoutManager(context)
                val videoSet = viewModel.videos ?: listOf()
                it.adapter = VideosAdapter(
                    videoSet = videoSet,
                    onCardLongClickListener = ::onCardLongClickListener,
                    onCardClickListener = ::onCardClickListener
                )
            }
        }

        return binding.root
    }

    private fun onCardLongClickListener(card: MaterialCardView, video: Video) {
        if (card.isChecked) {
            deselectVideo(card = card, video = video)
        } else {
            selectVideo(card = card, video = video)
        }
    }

    private fun selectVideo(card: MaterialCardView, video: Video) {
        card.isChecked = true
        viewModel.selectVideo(video = video)
    }

    private fun deselectVideo(card: MaterialCardView, video: Video) {
        card.isChecked = false
        viewModel.deselectVideo(video = video)
    }

    private fun onCardClickListener(card: MaterialCardView, video: Video) {
        if (card.isChecked) {
            deselectVideo(card = card, video = video)
        } else if (viewModel.isAnyVideoSelected()) {
            selectVideo(card = card, video = video)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addVideoButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_videoList_to_addVideo)
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
