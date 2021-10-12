package com.example.belltakehome.network.requests

import android.util.Log
import com.example.belltakehome.models.MoviePostersResponse
import com.example.belltakehome.network.APIClient
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePostersRequest {

    private val TAG = MoviePostersRequest::class.java.simpleName

    suspend fun getMoviePosters(alias: String): List<MoviePostersResponse>? {
        val result = CompletableDeferred<List<MoviePostersResponse>?>()

        withContext(Dispatchers.IO) {
            APIClient.bellMockAPIObject
                .getMoviePosters(alias)
                .enqueue(object: Callback<List<MoviePostersResponse>> {
                    override fun onResponse(call: Call<List<MoviePostersResponse>>, response: Response<List<MoviePostersResponse>>) {
                        response.body()?.let {
                            result.complete(it)
                        }
                    }

                    override fun onFailure(call: Call<List<MoviePostersResponse>>, t: Throwable) {
                        Log.e(TAG, t.message.toString())
                        result.complete(null)
                    }
                })
        }

        return result.await()
    }
}