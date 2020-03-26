package dev.shreyaspatil.foodium.ui.details

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import coil.api.load
import dev.shreyaspatil.foodium.databinding.ActivityPostDetailsBinding
import dev.shreyaspatil.foodium.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_post_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class PostDetailsActivity : BaseActivity<PostDetailsViewModel, ActivityPostDetailsBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(mViewBinding.root)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val postId = intent.extras?.getInt(POST_ID)
            ?: throw IllegalArgumentException("postId must be non-null")

        initPost(postId)
    }

    private fun initPost(postId: Int) {
        mViewModel.getPost(postId).observe(this, Observer { post ->
            mViewBinding.postContent.apply {
                postTitle.text = post.title
                postAuthor.text = post.author
                postBody.text = post.body
            }
            mViewBinding.imageView.load(post.imageUrl)
        })
    }

    companion object {
        const val POST_ID = "postId"
    }

    override fun getViewBinding(): ActivityPostDetailsBinding =
        ActivityPostDetailsBinding.inflate(layoutInflater)

    override val mViewModel: PostDetailsViewModel by viewModel()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}