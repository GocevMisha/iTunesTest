package com.gocdev.itunestestapp.ui.description

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.gocdev.itunestestapp.vo.Album
import com.gocdev.itunestestapp.vo.Song
import com.gocdev.itunestestapp.repository.DescriptionRepository
import com.gocdev.itunestestapp.util.AbsentLiveData
import com.gocdev.itunestestapp.vo.Resource
import java.util.*
import javax.inject.Inject

class DescriptionActivityViewModel @Inject constructor(private val descriptionRepository : DescriptionRepository) : ViewModel() {
    private val id = MutableLiveData<String>()

    val songs: LiveData<Resource<List<Song>>> = id.switchMap { id ->
        if (id.isBlank()) {
            AbsentLiveData.create()
        } else {
            Log.d("songs", id)
            descriptionRepository.searchSongs(id)
        }

    }
    val album: LiveData<Album> = id.switchMap { id ->
        if (id.isBlank()) {
            AbsentLiveData.create()
        } else {
            descriptionRepository.getAlbumByID(id)
        }

    }
    fun setId(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == id.value) {
            return
        }
        id.value = input
    }


}