package dev.shreyaspatil.foodium.ui.details

import androidx.lifecycle.asLiveData
import dev.shreyaspatil.foodium.data.repository.PostsRepository
import dev.shreyaspatil.foodium.ui.base.BaseViewModel

/**
 * ViewModel for [PostDetailsActivity]
 */
class PostDetailsViewModel constructor(private val postsRepository: PostsRepository) :
    BaseViewModel() {

    fun getPost(id: Int) = postsRepository.getPostById(id).asLiveData()
}