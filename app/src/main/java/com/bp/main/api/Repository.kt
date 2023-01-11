package com.bp.main.api

import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: WebService,
) {
    fun getPhoto(id: Int) = apiCall { apiService.getPhoto(id) }
    fun getPhotos() = apiCall { apiService.getPhotos() }
}