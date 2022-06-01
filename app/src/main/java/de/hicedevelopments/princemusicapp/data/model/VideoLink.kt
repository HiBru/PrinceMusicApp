package de.hicedevelopments.princemusicapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import de.hicedevelopments.princemusicapp.util.TimeDurationFormatterProvider

data class VideoLink(
    @SerializedName("uri")
    @Expose
    var uri: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("duration")
    @Expose
    var duration: Int? = null,
    @SerializedName("embed")
    @Expose
    var embed: Boolean? = null,
)