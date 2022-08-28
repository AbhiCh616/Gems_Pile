package com.example.gemspile.data.data_mapper

import com.example.gemspile.local_source.table.VideoRow
import com.example.gemspile.validated_model.Video

fun Video.toVideoRow() = VideoRow(
    url = url
)

fun VideoRow.toVideo() = Video(
    url = url
)

fun List<VideoRow>.toVideoList() = this.map {
    it.toVideo()
}