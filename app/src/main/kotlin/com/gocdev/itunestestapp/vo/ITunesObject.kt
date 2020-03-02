package com.gocdev.itunestestapp.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gocdev.itunestestapp.db.ITunesTypeConverters
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Object with multiple type. Is used for converting response list with different types of objects
 */
sealed class ITunesObject(@Transient@Json(name="wrapperType") val wrapperType: WrapperType?)

@Entity(tableName = "albums")
@JsonClass(generateAdapter = true)
@TypeConverters(ITunesTypeConverters::class)
class Album(
    @PrimaryKey
    @field:SerializedName("collectionId") val collectionId : Int,
    @field:SerializedName("collectionType") val collectionType : String?,
    @field:SerializedName("artistId") val artistId : Int?,
    @field:SerializedName("amgArtistId") val amgArtistId : Int?,
    @field:SerializedName("artistName") val artistName : String?,
    @field:SerializedName("collectionName") val collectionName : String?,
    @field:SerializedName("collectionCensoredName") val collectionCensoredName : String?,
    @field:SerializedName("artistViewUrl") val artistViewUrl : String?,
    @field:SerializedName("collectionViewUrl") val collectionViewUrl : String?,
    @field:SerializedName("artworkUrl60") val artworkUrl60 : String?,
    @field:SerializedName("artworkUrl100") val artworkUrl100 : String?,
    @field:SerializedName("collectionPrice") val collectionPrice : Double?,
    @field:SerializedName("collectionExplicitness") val collectionExplicitness : String?,
    @field:SerializedName("trackCount") val trackCount : Int?,
    @field:SerializedName("copyright") val copyright : String?,
    @field:SerializedName("country") val country : String?,
    @field:SerializedName("currency") val currency : String?,
    @field:SerializedName("releaseDate") val releaseDate : String?,
    @field:SerializedName("primaryGenreName") val primaryGenreName : String?
) : ITunesObject(WrapperType.collection)

@Entity(tableName = "songs")
@JsonClass(generateAdapter = true)
@TypeConverters(ITunesTypeConverters::class)
class Song(
    @SerializedName("kind") val kind : String,
    @SerializedName("artistId") val artistId : Int,
    @SerializedName("collectionId") val collectionId : Int,
    @PrimaryKey
    @SerializedName("trackId") val trackId : Int,
    @SerializedName("artistName") val artistName : String,
    @SerializedName("collectionName") val collectionName : String,
    @SerializedName("trackName") val trackName : String,
    @SerializedName("collectionCensoredName") val collectionCensoredName : String,
    @SerializedName("trackCensoredName") val trackCensoredName : String,
    @SerializedName("artistViewUrl") val artistViewUrl : String,
    @SerializedName("collectionViewUrl") val collectionViewUrl : String,
    @SerializedName("trackViewUrl") val trackViewUrl : String,
    @SerializedName("previewUrl") val previewUrl : String,
    @SerializedName("artworkUrl30") val artworkUrl30 : String,
    @SerializedName("artworkUrl60") val artworkUrl60 : String,
    @SerializedName("artworkUrl100") val artworkUrl100 : String,
    @SerializedName("collectionPrice") val collectionPrice : Double,
    @SerializedName("trackPrice") val trackPrice : Double,
    @SerializedName("releaseDate") val releaseDate : String,
    @SerializedName("collectionExplicitness") val collectionExplicitness : String,
    @SerializedName("trackExplicitness") val trackExplicitness : String,
    @SerializedName("discCount") val discCount : Int,
    @SerializedName("discNumber") val discNumber : Int,
    @SerializedName("trackCount") val trackCount : Int,
    @SerializedName("trackNumber") val trackNumber : Int,
    @SerializedName("trackTimeMillis") val trackTimeMillis : Int,
    @SerializedName("country") val country : String,
    @SerializedName("currency") val currency : String,
    @SerializedName("primaryGenreName") val primaryGenreName : String,
    @SerializedName("isStreamable") val isStreamable : Boolean
) : ITunesObject(WrapperType.track)


@TypeConverters(ITunesTypeConverters::class)
enum class WrapperType {
    track,
    collection
}