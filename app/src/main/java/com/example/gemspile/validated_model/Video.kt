package com.example.gemspile.validated_model

import com.example.gemspile.validation.validatedVideoUrl

data class Video(
    val url: String
) {
    init {
        validatedVideoUrl(url = url)
    }
}