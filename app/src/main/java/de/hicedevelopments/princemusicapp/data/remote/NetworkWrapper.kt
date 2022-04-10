package de.hicedevelopments.princemusicapp.data.remote

import retrofit2.Response

data class NetworkWrapper<T>(
    private val response: Response<T>? = null
) {
    val model: T? = response?.body()
    val state: State

    init {
        state = when(response?.code()) {
            in 200..299 -> State.Success
            404 -> State.NotFoundError
            in 400..499 -> State.ClientError
            in 500..599 -> State.ServerError
            else -> State.UnknownError
        }
    }

    enum class State {
        Success,
        ClientError,
        NotFoundError,
        ServerError,
        UnknownError
    }
}