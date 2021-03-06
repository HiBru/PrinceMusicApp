package de.hicedevelopments.princemusicapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Urls(
    @SerializedName("last")
    @Expose
    var last: String? = null,
    @SerializedName("next")
    @Expose
    var next: String? = null
)