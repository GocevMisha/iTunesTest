package com.gocdev.itunestestapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gocdev.itunestestapp.vo.Album
import com.gocdev.itunestestapp.vo.Song
import com.gocdev.itunestestapp.vo.AlbumSearchResult

/**
 * Main database description.
 */
@Database(
    entities = [
        Album::class,
        AlbumSearchResult::class,
        Song::class],
    version = 8,
    exportSchema = false
)
abstract class ITunesDb : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    abstract fun songDao(): SongDao
}