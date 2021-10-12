package com.example.belltakehome.models

data class MoviePostersResponse(
    var id: String? = null,
    var axisId: Int? = null,
    var title: String? = null,
    var mediaType: String? = null,
    var summary: String? = null,
    var description: String? = null,
    var images: List<Image>? = null,
    var agvotCode: String? = null
)
