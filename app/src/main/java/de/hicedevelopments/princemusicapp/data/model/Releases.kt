package de.hicedevelopments.princemusicapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Releases(
    @SerializedName("pagination")
    @Expose
    var pagination: Pagination? = null,
    @SerializedName("releases")
    @Expose
    var releases: List<Release>? = null
)
