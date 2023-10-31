package com.georfernandez.openpayfit.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georfernandez.domain.entity.Movie
import com.georfernandez.domain.usecases.GetMostPopularMoviesUseCase
import com.georfernandez.domain.usecases.GetRecommendedMoviesByIdUseCase
import com.georfernandez.domain.usecases.GetTopRatedMoviesUseCase
import com.georfernandez.domain.utils.CoroutineResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMovieListUseCase: GetMostPopularMoviesUseCase,
    private val getTopRatedMovieListUseCase: GetTopRatedMoviesUseCase,
    private val getRecommendedMoviesListByIdUseCase: GetRecommendedMoviesByIdUseCase,
) : ViewModel() {
    private var mutableMoviesState = MutableLiveData<MoviesData>()
    fun getMoviesState(): LiveData<MoviesData> = mutableMoviesState
    private val loading: MutableLiveData<Boolean> = MutableLiveData()
    fun isLoading(): LiveData<Boolean> = loading
    fun fetchPopularMovies() =
        viewModelScope.launch {
            withContext(Dispatchers.IO) { getPopularMovieListUseCase() }.let { result ->
                when (result) {
                    is CoroutineResult.Success -> {
                        if (result.data.isNotEmpty()) {
                            mutableMoviesState.postValue(MoviesData(state = MoviesState.SHOW_POPULAR_MOVIES, popularMovies = result.data))
                        } else {
                            mutableMoviesState.postValue(MoviesData(state = MoviesState.EMPTY_STATE))
                        }
                    }

                    is CoroutineResult.Failure -> {
                        mutableMoviesState.postValue(MoviesData(state = MoviesState.ON_ERROR, exception = result.exception))
                    }
                }
            }
        }

    fun fetchTopRatedMovies() =
        viewModelScope.launch {
            withContext(Dispatchers.IO) { getTopRatedMovieListUseCase() }.let { result ->
                when (result) {
                    is CoroutineResult.Success -> {
                        if (result.data.isNotEmpty()) {
                            mutableMoviesState.postValue(
                                MoviesData(
                                    state = MoviesState.SHOW_TOP_RATED_MOVIES,
                                    topRatedMovies = result.data,
                                ),
                            )
                        } else {
                            mutableMoviesState.postValue(
                                MoviesData(
                                    state = MoviesState.EMPTY_STATE,
                                ),
                            )
                        }
                    }

                    is CoroutineResult.Failure -> {
                        mutableMoviesState.postValue(MoviesData(state = MoviesState.ON_ERROR, exception = result.exception))
                    }
                }
            }
        }

    fun fetchRecommendationsMovies(mostRatedMovie: Movie) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) { getRecommendedMoviesListByIdUseCase(mostRatedMovie.id) }.let { result ->
                when (result) {
                    is CoroutineResult.Success -> {
                        if (result.data.isNotEmpty()) {
                            mutableMoviesState.postValue(
                                MoviesData(state = MoviesState.SHOW_RECOMMENDED_MOVIES, recommendationMovies = result.data),
                            )
                        } else {
                            mutableMoviesState.postValue(
                                MoviesData(
                                    state = MoviesState.EMPTY_STATE,
                                ),
                            )
                        }
                    }

                    is CoroutineResult.Failure -> {
                        mutableMoviesState.postValue(MoviesData(state = MoviesState.ON_ERROR, exception = result.exception))
                    }
                }
            }
        }

    data class MoviesData(
        val state: MoviesState,
        val popularMovies: List<Movie> = emptyList(),
        val topRatedMovies: List<Movie> = emptyList(),
        val recommendationMovies: List<Movie> = emptyList(),
        val exception: Exception? = null,
    )

    enum class MoviesState {
        SHOW_POPULAR_MOVIES,
        SHOW_TOP_RATED_MOVIES,
        SHOW_RECOMMENDED_MOVIES,
        ON_ERROR,
        EMPTY_STATE,
    }
}
