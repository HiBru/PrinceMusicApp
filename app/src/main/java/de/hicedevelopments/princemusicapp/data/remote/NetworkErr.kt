package de.hicedevelopments.princemusicapp.data.remote

import androidx.annotation.StringRes
import de.hicedevelopments.princemusicapp.R

class NetworkErr(@StringRes val title: Int, @StringRes val desc: Int) {
    companion object {
        fun onClientError(): NetworkErr = NetworkErr(R.string.client_error_title, R.string.client_error_desc)
        fun onNotFoundError(): NetworkErr = NetworkErr(R.string.not_found_error_title, R.string.not_found_error_desc)
        fun onServerError(): NetworkErr = NetworkErr(R.string.server_error_title, R.string.server_error_desc)
        fun onNetworkError(): NetworkErr = NetworkErr(R.string.network_error_title, R.string.network_error_desc)
        fun onUnknownError(): NetworkErr = NetworkErr(R.string.unknown_error_title, R.string.unknown_error_desc)
    }
}