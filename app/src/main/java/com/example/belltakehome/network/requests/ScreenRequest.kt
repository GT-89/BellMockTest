package com.example.belltakehome.network.requests

import android.util.Log
import com.example.belltakehome.models.ScreenResponse
import com.example.belltakehome.network.APIClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class responsible for handling the screen list API network call
 * using coroutines to transfer work load to IO thread
 */
class ScreenRequest {

    private val TAG = ScreenRequest::class.java.simpleName

    suspend fun getScreens(): List<ScreenResponse>? {
        val result = CompletableDeferred<List<ScreenResponse>?>()
        withContext(Dispatchers.IO) {
            APIClient.bellMockAPIObject
                .getScreenList()
                .enqueue(object: Callback<List<ScreenResponse>> {
                    override fun onResponse(call: Call<List<ScreenResponse>>, response: Response<List<ScreenResponse>>) {
                        response.body()?.let {
                            result.complete(it)
                        }
                    }

                    override fun onFailure(call: Call<List<ScreenResponse>>, t: Throwable) {
                        Log.e(TAG, t.message.toString())
                        result.complete(null)
                    }
                })


        }
        return result.await()
    }
}