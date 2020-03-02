package com.gocdev.itunestestapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gocdev.itunestestapp.vo.Song

/**
 * Interface for database access on Song related operations.
 */
@Dao
abstract class SongDao {
    //Get list of song by album id
    @Query("SELECT * FROM songs WHERE `collectionId` = :id")
    abstract fun searchById(id: String): LiveData<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(songs: List<Song>)
}