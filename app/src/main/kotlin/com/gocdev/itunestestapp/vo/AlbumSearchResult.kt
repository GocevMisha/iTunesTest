package com.gocdev.itunestestapp.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gocdev.itunestestapp.db.ITunesTypeConverters

/**
 * Entity with result of searching; is used for searching albums according with query
 */
@Entity(tableName = "searches")
@TypeConverters(ITunesTypeConverters::class)
class AlbumSearchResult(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val query: String,
    var albumIds: List<Int>,
    var totalCount: Int
)