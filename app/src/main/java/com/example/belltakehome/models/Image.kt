package com.example.belltakehome.models

enum class ImageFormat {
    POSTER,
    THUMBNAIL
}

data class Image(
    var format: ImageFormat? = null,
    var url: String? = null
)
