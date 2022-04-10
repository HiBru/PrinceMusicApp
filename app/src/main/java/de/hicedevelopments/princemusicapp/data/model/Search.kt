package de.hicedevelopments.princemusicapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("pagination")
    @Expose
    var pagination: Pagination? = null,
    @SerializedName("results")
    @Expose
    var results: List<Result>? = null
)