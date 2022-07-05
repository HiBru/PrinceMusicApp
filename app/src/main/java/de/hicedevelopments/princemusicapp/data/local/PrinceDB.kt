package de.hicedevelopments.princemusicapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.hicedevelopments.princemusicapp.data.local.dao.AboutDao
import de.hicedevelopments.princemusicapp.data.local.dao.ReleaseDao
import de.hicedevelopments.princemusicapp.data.model.*

@Database(
    entities = [
        Release::class,
        About::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PrinceDB : RoomDatabase() {
    abstract fun releaseDao(): ReleaseDao
    abstract fun aboutDao(): AboutDao
}