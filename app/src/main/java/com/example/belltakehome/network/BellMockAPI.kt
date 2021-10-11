package com.example.belltakehome.network

import com.example.belltakehome.models.ScreenDetailsResponse
import com.example.belltakehome.models.ScreenResponse
import retrofit2.Call
import retrofit2.http.GET

interface BellMockAPI {

    /**
     * API Call to retrieve list of screens
     */
    @GET("mobile/v2/apps/screens")
    fun getScreenList(): Call<ScreenResponse>

    /**
     * API Call to retrieve screen details
     */
    @GET("mobile/v2/apps?")
    fun getScreenDetails(): Call<ScreenDetailsResponse>

}