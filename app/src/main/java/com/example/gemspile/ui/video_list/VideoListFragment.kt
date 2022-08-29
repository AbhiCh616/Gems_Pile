package com.example.gemspile.ui.video_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gemspile.R
import com.example.gemspile.databinding.VideoListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideoListFragment : Fragment() {
    private val viewModel: VideoListViewModel by viewModels()
    private lateinit var binding: VideoListBinding
    private lateinit var adapter: VideosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VideoListBinding.inflate(inflater, container, false)

        adapter = VideosAdapter(
            videoSet = mutableListOf(),
            onCardLongClickListener = viewModel::onCardLongClick,
            onCardClickListener = viewModel::onCardClick
        )

        binding.rvVideos.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.videoItems.collect { videoList ->
                    adapter.updateData(updatedVideoList = videoList)
                }
            }
        }

        binding.addVideoButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_videoList_to_addVideo)
        }
    }
}
