package com.bp.main.viewModel


import androidx.lifecycle.ViewModel
import com.bp.main.api.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getPhoto(id: Int) = repository.getPhoto(id)
    fun getPhotos() = repository.getPhotos()
}