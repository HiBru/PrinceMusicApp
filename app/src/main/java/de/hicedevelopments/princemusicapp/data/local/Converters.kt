package de.hicedevelopments.princemusicapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import de.hicedevelopments.princemusicapp.data.model.Alias
import de.hicedevelopments.princemusicapp.data.model.Community
import de.hicedevelopments.princemusicapp.data.model.Image

object Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromCommunity(value: Community): String = Gson().toJson(value)

    @TypeConverter
    fun toCommunity(value: String): Community = Gson().fromJson(value, Community::class.java)

    @TypeConverter
    fun fromAliasList(value: List<Alias>): String = Gson().toJson(value)

    @TypeConverter
    fun toAliasList(value: String): List<Alias> = Gson().fromJson(value, Array<Alias>::class.java).toList()

    @TypeConverter
    fun fromImageList(value: List<Image>): String = Gson().toJson(value)

    @TypeConverter
    fun toImageList(value: String): List<Image> = Gson().fromJson(value, Array<Image>::class.java).toList()
}