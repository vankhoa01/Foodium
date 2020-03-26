package dev.shreyaspatil.foodium.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.shreyaspatil.foodium.data.repository.PostsRepository
import dev.shreyaspatil.foodium.model.Post
import dev.shreyaspatil.foodium.ui.base.BaseViewModel
import dev.shreyaspatil.foodium.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for [MainActivity]
 */

class MainViewModel constructor(private val postsRepository: PostsRepository) :
    BaseViewModel() {

    private val _postsLiveData = MutableLiveData<State<List<Post>>>()

    val postsLiveData: LiveData<State<List<Post>>>
        get() = _postsLiveData

    fun getPosts() {
        viewModelScope.launch {
            postsRepository.getAllPosts().collect {
                _postsLiveData.value = it
            }
        }
    }

}
