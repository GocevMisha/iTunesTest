package com.gocdev.itunestestapp.api

import com.gocdev.itunestestapp.vo.ITunesObject
import com.squareup.moshi.Json

/**
 * Object to hold responses. This object different from the Entity in database because we store search result and list of result's object separately
 */
class Response(
    @Json(name = "resultCount")
    val count: Int,
    @Json(name = "results")
    val results: List<ITunesObject>
)