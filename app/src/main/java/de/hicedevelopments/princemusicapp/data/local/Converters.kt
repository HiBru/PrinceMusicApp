package de.hicedevelopments.princemusicapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import de.hicedevelopments.princemusicapp.data.model.*

object Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromImageList(value: List<Image>): String = Gson().toJson(value)

    @TypeConverter
    fun toImageList(value: String): List<Image> = Gson().fromJson(value, Array<Image>::class.java).toList()

    @TypeConverter
    fun fromAboutType(value: AboutType): Int = value.ordinal

    @TypeConverter
    fun toAboutType(value: Int): AboutType = AboutType.values()[value]

    @TypeConverter
    fun fromWebLinkList(value: List<WebLink>): String = Gson().toJson(value)

    @TypeConverter
    fun toWebLinkList(value: String): List<WebLink> = Gson().fromJson(value, Array<WebLink>::class.java).toList()
}