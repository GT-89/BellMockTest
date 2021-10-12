package com.example.belltakehome.network

import com.example.belltakehome.models.MoviePostersResponse
import com.example.belltakehome.models.ScreenDetailsResponse
import com.example.belltakehome.models.ScreenResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BellMockAPI {

    /**
     * API Call to retrieve list of screens
     */
    @GET("mobile/v2/apps/screens")
    fun getScreenList(): Call<List<ScreenResponse>>

    /**
     * API Call to retrieve screen details
     */
    @GET("mobile/v2/apps?")
    fun getScreenDetails(@Query("alias") alias: String): Call<List<ScreenDetailsResponse>>

    /**
     * API Call to retrieve movie posters
     */
    @GET("mobile/v2/apps?")
    fun getMoviePosters(@Query("alias") alias: String): Call<List<MoviePostersResponse>>

}