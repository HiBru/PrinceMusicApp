package de.hicedevelopments.princemusicapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.hicedevelopments.princemusicapp.data.local.dao.ReleaseDao
import de.hicedevelopments.princemusicapp.data.model.Community
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.model.Result

@Database(
    entities = [
        Release::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PrinceDB : RoomDatabase() {
    abstract fun releaseDao(): ReleaseDao
}