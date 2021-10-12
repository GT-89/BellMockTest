package com.example.belltakehome.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class responsible for handling the creation and handling of
 * the retrofit client object
 */
object APIClient {

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val bellRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.BELL_MOCK_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val bellMockAPIObject: BellMockAPI by lazy {
        bellRetrofit.create(BellMockAPI::class.java)
    }

}