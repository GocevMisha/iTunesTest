package com.gocdev.itunestestapp.db

import android.util.SparseIntArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gocdev.itunestestapp.vo.Album
import com.gocdev.itunestestapp.vo.AlbumSearchResult

/**
 * Interface for database access on Album related operations.
 */
@Dao
abstract class AlbumDao {
    //get search results bu query
    @Query("SELECT * FROM searches WHERE `query` = :query")
    abstract fun search(query: String): LiveData<AlbumSearchResult?>

    //get list of albums by album id; is used for loadOrdered
    @Query("SELECT * FROM albums WHERE collectionId in (:albumIds) ORDER BY collectionName DESC")
    protected abstract fun loadById(albumIds: List<Int>): LiveData<List<Album>>
    //get single album by id
    @Query("SELECT * FROM albums WHERE `collectionId` = :id")
    abstract fun getAlbumById(id: String): LiveData<Album>

    //insert search results
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertResults(result: AlbumSearchResult)

    //get all albums with ids which are stored in album search results
    fun loadOrdered(albumIds: List<Int>): LiveData<List<Album>> {
        val order = SparseIntArray()
        albumIds.withIndex().forEach {
            order.put(it.value, it.index)
        }
        return loadById(albumIds).map { repositories ->
            repositories.sortedWith(compareBy { it.collectionName })
        }
    }
    //insert list of albums
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(albums: List<Album>)
}
