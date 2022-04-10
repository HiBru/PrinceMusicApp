package de.hicedevelopments.princemusicapp.app.extension

import android.content.Context
import android.net.ConnectivityManager

fun Context.isConnected(): Boolean = (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.activeNetworkInfo?.isConnected ?: false