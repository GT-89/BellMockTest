package com.example.belltakehome.network

import com.example.belltakehome.models.Movie
import com.example.belltakehome.models.ScreenDetailsResponse
import com.example.belltakehome.models.ScreenResponse
import com.example.belltakehome.network.requests.MoviePostersRequest
import com.example.belltakehome.network.requests.ScreenDetailsRequest
import com.example.belltakehome.network.requests.ScreenRequest
import toothpick.InjectConstructor


/**
 * Provider class responsible for retrieving all BellMock API
 * related data
 */
@InjectConstructor
class BellMockApiProvider {

    suspend fun getListOfScreens(): List<ScreenResponse>? {
        return ScreenRequest().getScreens()
    }

    suspend fun getScreenDetailsData(alias: String): List<ScreenDetailsResponse>? {
        return ScreenDetailsRequest().getScreenDetails(alias)
    }

    suspend fun getMoviePosters(alias: String): List<Movie> {
        val posterList = arrayListOf<Movie>()
        MoviePostersRequest()
            .getMoviePosters(alias)
            ?.forEach { posterList.add(Movie(it)) }
        return posterList
    }
}