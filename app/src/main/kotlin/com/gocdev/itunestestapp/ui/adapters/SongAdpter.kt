package com.gocdev.itunestestapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gocdev.itunestestapp.R
import com.gocdev.itunestestapp.vo.Song
import kotlinx.android.synthetic.main.item_song.view.*


class SongAdapter(private val context: Context) : ListAdapter<Song, RecyclerView.ViewHolder>(
    SongDiffCallback()
) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        view = layoutInflater.inflate(R.layout.item_song, parent, false)
        return SongItemViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SongItemViewHolder).bind(getItem(position),context)

    }


    class SongDiffCallback : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

    }


    class SongItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        fun bind(song: Song?, context: Context) {
            itemView.number.text = song?.trackNumber.toString()
            itemView.song_name_tv.text = song?.trackName
            if(!song?.trackExplicitness.equals("notExplicit"))
                itemView.explicit_icon.visibility = View.VISIBLE
            else itemView.explicit_icon.visibility = View.GONE
        }

    }


}