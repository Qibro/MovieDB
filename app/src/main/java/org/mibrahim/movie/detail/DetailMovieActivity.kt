package org.mibrahim.movie.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.mibrahim.movie.R
import org.mibrahim.movie.core.domain.model.Movie
import org.mibrahim.movie.core.utils.Constants.Companion.BASE_IMAGE_URL

class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)

        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            Glide.with(this@DetailMovieActivity)
                .load(BASE_IMAGE_URL+detailMovie.backgroundPath)
                .into(text_detail_image)
            text_detail_description.text = detailMovie.overview
            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}
