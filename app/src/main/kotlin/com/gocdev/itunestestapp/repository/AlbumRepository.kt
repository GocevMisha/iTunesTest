package com.gocdev.itunestestapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.gocdev.itunestestapp.AppExecutors
import com.gocdev.itunestestapp.api.Response
import com.gocdev.itunestestapp.api.ITunesService
import com.gocdev.itunestestapp.vo.Album
import com.gocdev.itunestestapp.vo.AlbumSearchResult
import com.gocdev.itunestestapp.db.AlbumDao
import com.gocdev.itunestestapp.db.ITunesDb
import com.gocdev.itunestestapp.util.AbsentLiveData
import com.gocdev.itunestestapp.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository that handles Album instances.
 */
@Singleton
class AlbumRepository @Inject constructor(private val appExecutors: AppExecutors, private val db: ITunesDb,
                                          private val albumDao: AlbumDao,
                                          private val ITunesService: ITunesService) {
    //Get data cashed albums
    fun searchAlbums ( query: String) : LiveData<Resource<List<Album>>> {
        return object : NetworkBoundResource<List<Album>, Response>(appExecutors) {
            override fun saveCallResult(item: Response) {
                val albumIds = item.results.map { (it as Album).collectionId}
                val albums = item.results.map { it as Album }
                val albumSearchResult =
                    AlbumSearchResult(
                        id = 0,
                        query = query,
                        albumIds = albumIds,
                        totalCount = item.count
                    )
                db.runInTransaction {
                    albumDao.insertResults(albumSearchResult)
                    albumDao.insertAll(albums)
                }

            }

            override fun shouldFetch(data: List<Album>?) = data == null

            //get search result and if not null,  searching albums with ids in search result
            override fun loadFromDb(): LiveData<List<Album>> {
                    return albumDao.search(query).switchMap { searchData ->
                        if (searchData != null) {
                        albumDao.loadOrdered(searchData.albumIds)
                    } else AbsentLiveData.create()
                }
            }

            override fun createCall() = ITunesService.searchByName(query, "album")
        }.asLiveData()
    }

}