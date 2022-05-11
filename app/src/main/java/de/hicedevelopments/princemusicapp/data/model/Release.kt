package de.hicedevelopments.princemusicapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "releases")
data class Release(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int,
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("type")
    @Expose
    var type: String?,
    @SerializedName("main_release")
    @Expose
    var main_release: Int,
    @SerializedName("artist")
    @Expose
    var artist: String?,
    @SerializedName("role")
    @Expose
    var role: String?,
    @SerializedName("resource_url")
    @Expose
    var resource_url: String?,
    @SerializedName("year")
    @Expose
    var year: Int,
    @SerializedName("thumb")
    @Expose
    var thumb: String?
)
