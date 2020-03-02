package com.gocdev.itunestestapp.ui.description

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gocdev.itunestestapp.AppExecutors
import com.gocdev.itunestestapp.R
import com.gocdev.itunestestapp.vo.Album
import com.gocdev.itunestestapp.ui.adapters.SongAdapter
import kotlinx.android.synthetic.main.activity_album_description.*
import java.text.SimpleDateFormat
import javax.inject.Inject
import kotlin.Exception

class DescriptionActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    lateinit var album: Album
    @Inject
    lateinit var appExecutors: AppExecutors

    val viewModel: DescriptionActivityViewModel by viewModels {
        viewModelFactory
    }
    private val songAdapter =
        SongAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_description)

        val id = intent.getStringExtra("id")
        if(id!=null) {
            Log.d("idOFALBUM", id)
            viewModel.setId(id)
            viewModel.album.observe(
                this, Observer {
                    album = it
                    val albumArtworkUrl = album?.artworkUrl100
                    Glide.with(this).load(albumArtworkUrl).into(icon_image)
                    album_name_tv.text = album.collectionName
                    artist_name_tv.text = album.artistName
                    genre_tv.text = album.primaryGenreName
                    try {
                        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(album.releaseDate)
                        date_tv.text = SimpleDateFormat("dd.MM.yyyy").format(date)
                    } catch (e: Exception){}
                }
            )
            song_rv.setHasFixedSize(true)
            song_rv.adapter = songAdapter

            viewModel.songs.observe(this, Observer {
                songAdapter.submitList(it?.data)
            })
        }
    }
}
