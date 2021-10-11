package com.example.belltakehome.network.requests

import android.util.Log
import com.example.belltakehome.models.ScreenDetailsResponse
import com.example.belltakehome.network.APIClient
import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScreenDetailsRequest {

    private val TAG = ScreenRequest::class.java.simpleName

    suspend fun getScreens(): ScreenDetailsResponse? {
        val result = CompletableDeferred<ScreenDetailsResponse?>()

        APIClient.bellMockAPIObject
            .getScreenDetails()
            .enqueue(object: Callback<ScreenDetailsResponse> {
                override fun onResponse(call: Call<ScreenDetailsResponse>, response: Response<ScreenDetailsResponse>) {
                    response.body()?.let {
                        result.complete(it)
                    }
                }

                override fun onFailure(call: Call<ScreenDetailsResponse>, t: Throwable) {
                    Log.e(TAG, t.message.toString())
                }
            })

        return result.await()
    }
}