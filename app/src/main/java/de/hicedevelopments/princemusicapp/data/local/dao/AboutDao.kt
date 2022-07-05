package de.hicedevelopments.princemusicapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.hicedevelopments.princemusicapp.data.model.About
import de.hicedevelopments.princemusicapp.data.model.AboutType
import kotlinx.coroutines.flow.Flow

@Dao
interface AboutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAboutItem(item: About)

    @Query("SELECT * FROM about WHERE type= :type")
    fun getAboutItem(type: AboutType): Flow<About>
}