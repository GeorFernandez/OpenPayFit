package com.georfernandez.openpayfit.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.georfernandez.domain.entity.Movie
import com.georfernandez.openpayfit.databinding.FragmentMoviesBinding
import com.georfernandez.openpayfit.util.ExceptionDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private lateinit var _binding: FragmentMoviesBinding
    private val binding get() = _binding
    private val moviesViewModel: MoviesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        moviesViewModel.getMoviesState().observe(viewLifecycleOwner, ::updateUI)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.fetchPopularMovies()
        moviesViewModel.fetchTopRatedMovies()
    }

    private fun updateUI(moviesData: MoviesViewModel.MoviesData?) {
        if (moviesData != null) {
            when (moviesData.state) {
                MoviesViewModel.MoviesState.SHOW_POPULAR_MOVIES -> {
                    showMovieInfo(moviesData.popularMovies)
                    binding.mostPopularRecyclerView.apply {
                        adapter = MoviesAdapter(moviesData.popularMovies)
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                }

                MoviesViewModel.MoviesState.SHOW_TOP_RATED_MOVIES -> {
                    showMovieInfo(moviesData.topRatedMovies)
                    binding.topRatedRecyclerView.apply {
                        adapter = MoviesAdapter(moviesData.topRatedMovies)
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                    moviesViewModel.fetchRecommendationsMovies(moviesData.topRatedMovies.maxBy { it.popularity })
                }

                MoviesViewModel.MoviesState.SHOW_RECOMMENDED_MOVIES -> {
                    showMovieInfo(moviesData.recommendationMovies)
                    binding.recommendedRecyclerView.apply {
                        adapter = MoviesAdapter(moviesData.recommendationMovies)
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                }

                MoviesViewModel.MoviesState.ON_ERROR -> {
                    ExceptionDialogFragment.newInstance("Error").show(
                        childFragmentManager,
                        ExceptionDialogFragment.EXCEPTION_DIALOG_FRAGMENT,
                    )
                    binding.loaderAnimation.visibility = View.GONE
                }

                MoviesViewModel.MoviesState.EMPTY_STATE -> {
                    binding.moviesEmptyState.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showMovieInfo(movie: List<Movie>) {
        binding.moviesEmptyState.visibility = View.GONE
        binding.loaderAnimation.visibility = View.GONE
        binding.movieContentContainer.visibility = View.VISIBLE
    }
}
