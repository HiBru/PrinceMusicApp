package de.hicedevelopments.princemusicapp.data.model

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.hicedevelopments.princemusicapp.R
import kotlinx.parcelize.Parcelize

@Entity(tableName = "about")
@Parcelize
data class About(
    @PrimaryKey
    val type: AboutType,
    val title: String?,
    val realName: String?,
    val nameVariations: List<String>?,
    val profile: String?,
    val links: List<WebLink>?,
    val images: List<Image>?
) : Parcelable {
    companion object {
        fun getAboutMeItem(context: Context): About = About(
                type = AboutType.ME,
                title = context.getString(R.string.about_me_title),
                realName = context.getString(R.string.about_me_real_name),
                nameVariations = context.resources.getStringArray(R.array.about_me_name_variations).toList(),
                profile = context.getString(R.string.about_me),
                links = AboutWebLink.values().asList().map { WebLink(it.text, context.getString(it.link)) },
                images = context.resources.getStringArray(R.array.about_me_images).asList().map { Image(uri = it) }
        )
    }
}

enum class AboutType {
    PRINCE,
    ME
}

enum class AboutWebLink(val text: String?, @StringRes val link: Int) {
    DEFSHOP("DefShop", R.string.defshop_link),
    DLR("DLR Moving Lab", R.string.dlr_moving_lab_link),
    HIGHERSELF("Higher Self - Laura Seiler Life Coaching", R.string.higher_self_link),
    FITX("FitX - Fitness App", R.string.fitx_link),
    PREMIUM("Premium Group Berlin", R.string.premium_link),
    BPA("BPA-Fahrt", R.string.bpa_link)
}
