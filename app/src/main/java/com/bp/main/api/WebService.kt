package com.bp.main.api

import com.bp.main.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("photos")
    suspend fun getPhoto(@Query("id") id: Int): MutableList<Photo>

    @GET("photos")
    suspend fun getPhotos(): MutableList<Photo>
}