package com.example.gemspile.ui.video_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gemspile.databinding.VideoItemBinding
import com.example.gemspile.validated_model.Video
import com.google.android.material.card.MaterialCardView

class VideosAdapter(
    private val videoSet: List<Video>,
    private val onCardLongClickListener: (MaterialCardView, Video) -> Unit
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
            videoCard.setOnLongClickListener {
                onCardLongClickListener(videoCard, videoSet[position])
                true
            }
        }
    }

    override fun getItemCount() = videoSet.size
}