package de.hicedevelopments.princemusicapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Identifier(
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("value")
    @Expose
    var value: String? = null
)