package de.hicedevelopments.princemusicapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.hicedevelopments.princemusicapp.data.model.Release
import de.hicedevelopments.princemusicapp.data.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface ReleaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Release>?): List<Long>

    @Query("SELECT * FROM releases")
    fun getReleases(): Flow<List<Release>?>

    @Query("DELETE FROM releases")
    fun clear()
}