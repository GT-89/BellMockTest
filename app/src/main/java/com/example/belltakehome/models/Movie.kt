package com.example.belltakehome.models

data class Movie (
    var id: String? = null,
    var title: String? = null,
    var summary: String? = null,
    var description: String? = null,
    var imageUrl: String? = null) {

    constructor(data: MoviePostersResponse): this() {
        id = data.id
        title = data.title
        summary = data.summary
        description = data.description
        data.images?.let {
            it.filter { it.format == ImageFormat.POSTER }
              imageUrl = it.first().url
        }
    }
}