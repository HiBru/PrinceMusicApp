package de.hicedevelopments.princemusicapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Community(
    @SerializedName("want")
    @Expose
    var want: Int = 0,
    @SerializedName("have")
    @Expose
    var have: Int = 0
)