package org.mibrahim.movie.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.mibrahim.movie.core.ui.MovieAdapter
import org.mibrahim.movie.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.mibrahim.movie.R
import org.mibrahim.movie.core.data.Resource

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.movie.observe(viewLifecycleOwner, Observer{ movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            view_error.visibility = View.VISIBLE
                            tv_error.text = movie.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(rv_movie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}
