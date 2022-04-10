package de.hicedevelopments.princemusicapp.data.remote

import de.hicedevelopments.princemusicapp.data.remote.NetworkWrapper.State.*
import de.hicedevelopments.princemusicapp.util.ConnectionHelper

interface ErrorHandler {

    val connectionHelper: ConnectionHelper

    fun handleException(exception: Exception) {
        if (!connectionHelper.isConnected()) {
            onNetworkErr(NetworkErr.onNetworkError())
        }
        else if (exception is NetworkException) {
            when (exception.state) {
                ClientError -> onNetworkErr(NetworkErr.onClientError())
                NotFoundError -> onNetworkErr(NetworkErr.onNotFoundError())
                ServerError -> onNetworkErr(NetworkErr.onServerError())
                UnknownError -> onNetworkErr(NetworkErr.onUnknownError())
                else -> onNetworkErr(NetworkErr.onUnknownError())
            }
        } else {
            onNetworkErr(NetworkErr.onUnknownError())
        }
    }

    fun onNetworkErr(err: NetworkErr)
}