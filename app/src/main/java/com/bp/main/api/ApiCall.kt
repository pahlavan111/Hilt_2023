package com.bp.main.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

private suspend fun <T> privateApiCall(
    emitter: RemoteErrorEmitter,
    responseFunction: suspend () -> T
): T? {
    try {
        return withTimeout(5000) {
            responseFunction()
        }

    } catch (e: Exception) {
        withContext(Dispatchers.Main) {
            e.printStackTrace()
            Log.e("ApiCal", "callError: ${e.localizedMessage}", e.cause)
            when (e) {
                is HttpException -> {
                    val body = e.response()?.errorBody()
                    emitter.onError(body.toString())
                }
                is TimeoutCancellationException -> {
                    emitter.onError(ErrorType.TIMEOUT)
                }
                is IOException -> {
                    emitter.onError(ErrorType.NETWORK)
                }
                else -> emitter.onError(ErrorType.UNKNOWN)
            }
        }
        return null
    }
}

 fun <T> apiCall(
    emitter: RemoteErrorEmitter,
    responseFunction: suspend () -> T
): LiveData<T?> {

    return liveData {
        val response = privateApiCall(emitter) {
            responseFunction()
        }
        emit(response)
    }
}
