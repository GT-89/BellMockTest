package com.example.belltakehome.network.requests

import android.util.Log
import com.example.belltakehome.models.ScreenDetailsResponse
import com.example.belltakehome.network.APIClient
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class responsible for handling the screen details API network call
 * using coroutines to transfer work load to IO thread
 */
class ScreenDetailsRequest {

    private val TAG = ScreenRequest::class.java.simpleName

    suspend fun getScreenDetails(alias: String): List<ScreenDetailsResponse>? {
        val result = CompletableDeferred<List<ScreenDetailsResponse>?>()

        withContext(Dispatchers.IO) {
            APIClient.bellMockAPIObject
                .getScreenDetails(alias)
                .enqueue(object: Callback<List<ScreenDetailsResponse>> {
                    override fun onResponse(call: Call<List<ScreenDetailsResponse>>, response: Response<List<ScreenDetailsResponse>>) {
                        response.body()?.let {
                            result.complete(it)
                        }
                    }

                    override fun onFailure(call: Call<List<ScreenDetailsResponse>>, t: Throwable) {
                        Log.e(TAG, t.message.toString())
                        result.complete(null)
                    }
                })
        }

        return result.await()
    }
}