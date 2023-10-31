package com.georfernandez.domain.service

import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.entity.Movie
import com.georfernandez.domain.utils.CoroutineResult

interface TMDBService {
    suspend fun getMostPopularActors(): CoroutineResult<List<Actor>>
    suspend fun getPopularMovies(): CoroutineResult<List<Movie>>
    suspend fun getRecommendedMoviesById(tMDBMovieId: Int): CoroutineResult<List<Movie>>
    suspend fun getTopRatedMovies(): CoroutineResult<List<Movie>>
}
