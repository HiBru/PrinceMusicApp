package de.hicedevelopments.princemusicapp.data.remote

import de.hicedevelopments.princemusicapp.data.remote.NetworkWrapper.State.*
import retrofit2.Response

data class NetworkWrapper<T>(
    private val response: Response<T>? = null
) {
    val model: T? = response?.body()
    val state = when(response?.code()) {
        in 200..299 -> Success
        404 -> NotFoundError
        in 400..499 -> ClientError
        in 500..599 -> ServerError
        else -> UnknownError
    }

    enum class State {
        Success,
        ClientError,
        NetworkError,
        NotFoundError,
        ServerError,
        UnknownError
    }

}