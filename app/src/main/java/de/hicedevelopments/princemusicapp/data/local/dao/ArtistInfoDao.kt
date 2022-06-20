package de.hicedevelopments.princemusicapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.hicedevelopments.princemusicapp.data.model.ArtistInfo

@Dao
interface ArtistInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(info: ArtistInfo?): Long

    @Query("SELECT * FROM artistInfo")
    fun getArtistInfo(): ArtistInfo?
}