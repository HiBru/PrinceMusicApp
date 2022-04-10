package de.hicedevelopments.princemusicapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "community")
data class Community(
    @SerializedName("want")
    @Expose
    var want: Int = 0,
    @SerializedName("have")
    @Expose
    var have: Int = 0,
    @PrimaryKey(autoGenerate = true)
    var id: Int
)