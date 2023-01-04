package com.bp.main.api

import com.bp.main.model.Post
import retrofit2.http.GET

interface WebService {

    @GET("posts")
    suspend fun getPosts():MutableList<Post>
}