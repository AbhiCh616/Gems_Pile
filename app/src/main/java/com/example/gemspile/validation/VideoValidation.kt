package com.example.gemspile.validation

import com.example.gemspile.exception.VideoUrlRequiredException

fun validatedVideoUrl(url: String?): String {
    if(url.isNullOrBlank()) {
        throw VideoUrlRequiredException()
    }
    return url
}