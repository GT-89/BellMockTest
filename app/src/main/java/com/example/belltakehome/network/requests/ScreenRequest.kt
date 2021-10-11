package com.example.belltakehome.network.requests

import android.util.Log
import com.example.belltakehome.models.ScreenResponse
import com.example.belltakehome.network.APIClient
import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScreenRequest {

    private val TAG = ScreenRequest::class.java.simpleName

    suspend fun getScreens(): ScreenResponse? {
        val result = CompletableDeferred<ScreenResponse?>()

        APIClient.bellMockAPIObject
            .getScreenList()
            .enqueue(object: Callback<ScreenResponse> {
                override fun onResponse(call: Call<ScreenResponse>, response: Response<ScreenResponse>) {
                    response.body()?.let {
                        result.complete(it)
                    }
                }

                override fun onFailure(call: Call<ScreenResponse>, t: Throwable) {
                    Log.e(TAG, t.message.toString())
                }
            })

        return result.await()
    }
}