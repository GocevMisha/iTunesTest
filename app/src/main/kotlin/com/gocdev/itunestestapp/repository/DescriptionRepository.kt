package com.gocdev.itunestestapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.gocdev.itunestestapp.AppExecutors
import com.gocdev.itunestestapp.api.Response
import com.gocdev.itunestestapp.api.ITunesService
import com.gocdev.itunestestapp.db.ITunesDb
import com.gocdev.itunestestapp.vo.Song
import com.gocdev.itunestestapp.db.AlbumDao
import com.gocdev.itunestestapp.util.AbsentLiveData
import com.gocdev.itunestestapp.api.ApiResponse
import com.gocdev.itunestestapp.db.SongDao
import com.gocdev.itunestestapp.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles Description instances.
 */
@Singleton
class DescriptionRepository @Inject constructor(private val appExecutors: AppExecutors,
                                                private val db: ITunesDb,
                                                private val albumDao: AlbumDao,
                                                private val songDao: SongDao,
                                                private val ITunesService: ITunesService
) {

    fun searchSongs (id: String) : LiveData<Resource<List<Song>>> {
        return object : NetworkBoundResource<List<Song>, Response>(appExecutors) {
            override fun saveCallResult(item: Response) {
                val songs = item.results.mapNotNull { it as? Song }
                db.runInTransaction {
                    songDao.insertAll(songs)
                }

            }

            override fun shouldFetch(data: List<Song>?) = data == null

            override fun loadFromDb(): LiveData<List<Song>> {
                return songDao.searchById(id).switchMap { searchData ->
                    searchData.forEach { Log.d("songs", it.trackName)}
                        if (searchData.any()) {
                            songDao.searchById(id)
                        } else AbsentLiveData.create()
                }
            }

            override fun createCall(): LiveData<ApiResponse<Response>> {
                Log.d("songer", id)
                return ITunesService.lookupById(id, "song")}
        }.asLiveData()
    }

    fun getAlbumByID(id: String) = albumDao.getAlbumById(id)


}