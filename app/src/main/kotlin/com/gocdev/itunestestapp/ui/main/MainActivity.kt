package com.gocdev.itunestestapp.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gocdev.itunestestapp.AppExecutors
import com.gocdev.itunestestapp.R
import com.gocdev.itunestestapp.ui.adapters.AlbumAdapter
import com.gocdev.itunestestapp.vo.Resource
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    @Inject
    lateinit var appExecutors: AppExecutors

    val viewModel: MainActivityViewModel by viewModels {
        viewModelFactory
    }

    private val albumAdapter =
        AlbumAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        album_rv.setHasFixedSize(true)
        album_rv.adapter = albumAdapter
        viewModel.albums.observe(this, Observer {
            albumAdapter.submitList(it?.data)

            progress_bar_popular.visibility = if (it?.data == null && it?.status == Resource.Status.LOADING)
                View.VISIBLE else View.GONE
            txt_error_popular.visibility = if ((it?.data?.isEmpty() == true && it.status == Resource.Status.SUCCESS)
                || (it?.data == null && it?.status == Resource.Status.ERROR))
                View.VISIBLE else View.GONE
            txt_error_popular.text = if (it?.data?.isEmpty() == true && it.status == Resource.Status.SUCCESS)
                "No results were found for your request!" else "Connection Problem!"

        })
        searchBar.setPlaceHolder("Search album in iTunes")
        searchBar.setPlaceHolderColor(ContextCompat.getColor(this, R.color.colorAccent))
        searchBar.setHint("Search")
        searchBar.setSpeechMode(false)
        searchBar.isSuggestionsEnabled = false
        searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener{

            override fun onButtonClicked(buttonCode: Int) {
                }

            override fun onSearchStateChanged(enabled: Boolean) {
                 }

            override fun onSearchConfirmed(text: CharSequence?) {
                viewModel.setQuery(text.toString())
                searchBar.closeSearch()
                searchBar.setPlaceHolder(text)
            }
        })

    }

}
