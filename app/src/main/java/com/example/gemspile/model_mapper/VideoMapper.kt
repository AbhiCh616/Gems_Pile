package com.example.gemspile.model_mapper

import com.example.gemspile.raw_model.VideoRaw
import com.example.gemspile.validated_model.Video
import com.example.gemspile.validation.validatedVideoUrl

fun VideoRaw.toValidatedVideo() = Video(
    url = validatedVideoUrl(url = url)
)