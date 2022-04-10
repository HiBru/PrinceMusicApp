package de.hicedevelopments.princemusicapp.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonProvider {
    val gsonInstance: Gson by lazy {
        GsonBuilder()
            .create()
    }
}