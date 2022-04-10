package de.hicedevelopments.princemusicapp.util

import android.content.Context
import de.hicedevelopments.princemusicapp.app.extension.isConnected

class ConnectionHelper(private val context: Context) {
    fun isConnected(): Boolean = context.isConnected()
}