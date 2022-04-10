package de.hicedevelopments.princemusicapp.data.remote

import android.util.Log
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class NetworkWrapperCallAdapter<T : Any> (private val responseType: Type) : CallAdapter<T, NetworkWrapper<T>> {

    private val TAG = this.javaClass.simpleName

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<T>): NetworkWrapper<T> {
        return try {
            val response = call.execute()
            if (!response.isSuccessful) {
                logResponseError(response)
            }
            NetworkWrapper(response)
        } catch (e: IOException) {
            logRequestError(e)
            NetworkWrapper(null)
        }
    }

    private fun logRequestError(e: Exception) {
        Log.e(TAG, "Error request ${e.message}")
    }

    private fun logResponseError(response: Response<*>) {
        Log.e(TAG, "Error response ${response.code()} ${response.message()}")
    }
}