package com.gocdev.itunestestapp.api

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * REST API access points
 */
interface ITunesService {

    @GET("search")
    fun searchByName(@Query("term") name: String,
                     @Query("entity") type: String): LiveData<ApiResponse<Response>>

    @GET("lookup")
    fun lookupById(@Query("id") name: String,
                      @Query("entity") type: String): LiveData<ApiResponse<Response>>
}