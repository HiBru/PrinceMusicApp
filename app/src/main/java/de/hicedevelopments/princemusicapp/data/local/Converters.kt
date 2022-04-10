package de.hicedevelopments.princemusicapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import de.hicedevelopments.princemusicapp.data.model.Community

object Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromCommunity(value: Community): String = Gson().toJson(value)

    @TypeConverter
    fun toCommunity(value: String): Community = Gson().fromJson(value, Community::class.java)
}