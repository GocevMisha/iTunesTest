package com.gocdev.itunestestapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.switchMap
import androidx.lifecycle.ViewModel
import com.gocdev.itunestestapp.vo.Album
import com.gocdev.itunestestapp.repository.AlbumRepository
import com.gocdev.itunestestapp.util.AbsentLiveData
import com.gocdev.itunestestapp.vo.AlbumSearchResult
import com.gocdev.itunestestapp.vo.Resource
import java.util.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val albumRepository : AlbumRepository) : ViewModel() {
    private val _query = MutableLiveData<String>()

    val albums: LiveData<Resource<List<Album>>> = _query.switchMap { search ->
        if (search.isBlank()) {
            Log.d("error", "1")
            AbsentLiveData.create()
        } else {
            albumRepository.searchAlbums(search)
        }

    }
    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == _query.value) {
            return
        }
        _query.value = input
    }



}