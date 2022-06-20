package de.hicedevelopments.princemusicapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Alias(
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("resource_url")
    @Expose
    var resourceUrl: String? = null
) : Parcelable
