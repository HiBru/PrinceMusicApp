package de.hicedevelopments.princemusicapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "artistInfo")
@Parcelize
data class ArtistInfo(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int = 0,
    @SerializedName("resource_url")
    @Expose
    var resourceUrl: String? = null,
    @SerializedName("uri")
    @Expose
    var uri: String? = null,
    @SerializedName("releases_url")
    @Expose
    var releasesUrl: String? = null,
    @SerializedName("images")
    @Expose
    var images: List<Image>? = null,
    @SerializedName("realname")
    @Expose
    var realName: String? = null,
    @SerializedName("profile")
    @Expose
    var profile: String? = null,
    @SerializedName("urls")
    @Expose
    var urls: List<String>? = null,
    @SerializedName("namevariations")
    @Expose
    var nameVariations: List<String>? = null,
    @SerializedName("aliases")
    @Expose
    var aliases: List<Alias>? = null,
    @SerializedName("data_quality")
    @Expose
    var dataQuality: String? = null
) : Parcelable
