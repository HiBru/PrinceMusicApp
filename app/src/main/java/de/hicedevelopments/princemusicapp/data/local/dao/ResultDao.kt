package de.hicedevelopments.princemusicapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.hicedevelopments.princemusicapp.data.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Result>?): List<Long>

    @Query("SELECT * FROM results")
    fun getResults(): Flow<List<Result>?>

    @Query("DELETE FROM results")
    fun clear()
}