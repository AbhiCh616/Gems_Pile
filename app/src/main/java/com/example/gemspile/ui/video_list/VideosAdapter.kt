package com.example.gemspile.ui.video_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gemspile.databinding.VideoItemBinding

class VideosAdapter(
    private val videoSet: MutableList<VideoItem>,
    private val onCardLongClickListener: (VideoItem) -> Unit,
    private val onCardClickListener: (VideoItem) -> Unit
) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {
    class ViewHolder(val binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VideoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            textView.text = videoSet[position].url
            videoCard.isChecked = videoSet[position].isSelected
            videoCard.setOnLongClickListener {
                onCardLongClickListener(videoSet[position])
                true
            }
            videoCard.setOnClickListener {
                onCardClickListener(videoSet[position])
            }
        }
    }

    fun updateData(updatedVideoList: List<VideoItem>) {
        val diffCallback = VideoItemDiffCallback(videoSet, updatedVideoList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        videoSet.clear()
        videoSet.addAll(updatedVideoList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = videoSet.size
}