package com.gocdev.itunestestapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gocdev.itunestestapp.ui.description.DescriptionActivity
import com.gocdev.itunestestapp.R
import com.gocdev.itunestestapp.vo.Album
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(private val context: Context) : ListAdapter<Album, RecyclerView.ViewHolder>(
    AlbumDiffCallback()
) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        view = layoutInflater.inflate(R.layout.item_album, parent, false)
        return AlbumItemViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AlbumItemViewHolder).bind(getItem(position),context)

    }


    class AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }

    }


    class AlbumItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        fun bind(album: Album?, context: Context) {
            itemView.title_tv.text = album?.collectionName
            itemView.artist_tv.text =  album?.artistName

            val albumArtworkUrl = album?.artworkUrl60
            Glide.with(itemView.context)
                .load(albumArtworkUrl)
                .into(itemView.icon_image)

            itemView.setOnClickListener{
                val intent = Intent(context, DescriptionActivity::class.java)
                intent.putExtra("id", album?.collectionId.toString())
                context.startActivity(intent)
            }
            if(!album?.collectionExplicitness.equals("notExplicit"))
                itemView.explicit_album_icon.visibility = View.VISIBLE
            else itemView.explicit_album_icon.visibility = View.GONE
        }

    }


}