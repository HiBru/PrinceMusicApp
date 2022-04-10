package de.hicedevelopments.princemusicapp.data.remote

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkWrapperCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)
        if (rawType != Call::class.java || returnType !is ParameterizedType) {
            return null
        }
        val upperBound = getParameterUpperBound(0, returnType)

        return if(upperBound is ParameterizedType && upperBound.rawType == NetworkWrapper::class.java) {
            val bodyType = getParameterUpperBound(0, upperBound)
            NetworkWrapperCallAdapter<Any>(bodyType)
        } else {
            null
        }
    }

    companion object {
        fun create() = NetworkWrapperCallAdapterFactory()
    }
}