package com.georfernandez.domain.database

import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.entity.Movie
import com.georfernandez.domain.utils.CoroutineResult

interface TMDBRepository {
    suspend fun insertActorToDB(popularPersons: List<Actor>)
    suspend fun getDBActors(): CoroutineResult<List<Actor>>
    suspend fun insertPopularMovieToDB(movies: List<Movie>)
    suspend fun getDBPopularMovie(): CoroutineResult<List<Movie>>
    suspend fun insertTopRatedMoviesToDB(movies: List<Movie>)
    suspend fun getDBTopRatedMovies(): CoroutineResult<List<Movie>>
    suspend fun insertRecommendedMoviesToDB(movieId: Int, movies: List<Movie>)
    suspend fun getDBGetRecommendedMoviesById(movieId: Int): CoroutineResult<List<Movie>>
}
