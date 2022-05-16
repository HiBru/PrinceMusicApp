package de.hicedevelopments.princemusicapp.data.remote

import androidx.annotation.StringRes
import de.hicedevelopments.princemusicapp.R
import de.hicedevelopments.princemusicapp.data.remote.NetworkWrapper.State.*

class NetworkErr(val errState: NetworkWrapper.State, @StringRes val title: Int, @StringRes val desc: Int) {
    companion object {
        fun onClientError(): NetworkErr = NetworkErr(ClientError, R.string.client_error_title, R.string.client_error_desc)
        fun onNotFoundError(): NetworkErr = NetworkErr(NotFoundError, R.string.not_found_error_title, R.string.not_found_error_desc)
        fun onServerError(): NetworkErr = NetworkErr(ServerError, R.string.server_error_title, R.string.server_error_desc)
        fun onNetworkError(): NetworkErr = NetworkErr(NetworkError, R.string.network_error_title, R.string.network_error_desc)
        fun onUnknownError(): NetworkErr = NetworkErr(UnknownError, R.string.unknown_error_title, R.string.unknown_error_desc)
    }
}