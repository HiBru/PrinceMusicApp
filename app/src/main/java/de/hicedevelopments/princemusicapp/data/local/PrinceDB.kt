package de.hicedevelopments.princemusicapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.hicedevelopments.princemusicapp.data.local.dao.ResultDao
import de.hicedevelopments.princemusicapp.data.model.Community
import de.hicedevelopments.princemusicapp.data.model.Result

@Database(
    entities = [
        Result::class,
        Community::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PrinceDB : RoomDatabase() {
    abstract fun resultDao(): ResultDao
}