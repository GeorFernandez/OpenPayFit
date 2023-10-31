package com.georfernandez.data.database

import com.georfernandez.data.database.dao.ActorDao
import com.georfernandez.data.database.dao.MovieStarringDao
import com.georfernandez.data.database.dao.PopularMovieDao
import com.georfernandez.data.database.dao.RecommendedMovieDao
import com.georfernandez.data.database.dao.TopRatedMovieDao
import com.georfernandez.data.mapper.mapToDataBaseActor
import com.georfernandez.data.mapper.mapToDataBaseKnownFor
import com.georfernandez.data.mapper.mapToDataBasePopularMovie
import com.georfernandez.data.mapper.mapToDataBaseRecommendedMovie
import com.georfernandez.data.mapper.mapToDataBaseTopRatedMovie
import com.georfernandez.data.mapper.mapToLocalActorList
import com.georfernandez.data.mapper.mapToLocalPopularMovieList
import com.georfernandez.data.mapper.mapToLocalRecommendedMovieList
import com.georfernandez.data.mapper.mapToLocalTopRatedMovieList
import com.georfernandez.domain.database.TMDBRepository
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.entity.Movie
import com.georfernandez.domain.utils.CoroutineResult

class TMDBRepositoryImpl(
    private val actorDao: ActorDao,
    private val movieStarringDao: MovieStarringDao,
    private val popularMovieDao: PopularMovieDao,
    private val topRatedMovieDao: TopRatedMovieDao,
    private val recommendedMovieDao: RecommendedMovieDao,
) : TMDBRepository {
    override suspend fun insertActorToDB(popularPersons: List<Actor>) {
        popularPersons.forEach { popularPerson ->
            actorDao.insertActor(popularPerson.mapToDataBaseActor())

            popularPerson.movieStarring.forEach {
                movieStarringDao.insertMovieStarring(it.mapToDataBaseKnownFor(popularPerson.id))
            }
        }
    }

    override suspend fun getDBActors(): CoroutineResult<List<Actor>> =
        actorDao.getDBActor().let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.mapToLocalActorList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }

    override suspend fun insertPopularMovieToDB(movies: List<Movie>) {
        movies.forEach { movie ->
            popularMovieDao.insertPopularMovie(movie.mapToDataBasePopularMovie())
        }
    }

    override suspend fun getDBPopularMovie(): CoroutineResult<List<Movie>> =
        popularMovieDao.getDBPopularMovies().let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.mapToLocalPopularMovieList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }

    override suspend fun insertTopRatedMoviesToDB(movies: List<Movie>) {
        movies.forEach { movie ->
            topRatedMovieDao.insertTopRatedMovie(movie.mapToDataBaseTopRatedMovie())
        }
    }

    override suspend fun getDBTopRatedMovies(): CoroutineResult<List<Movie>> =
        topRatedMovieDao.getDBTopRatedMovies().let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.mapToLocalTopRatedMovieList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }

    override suspend fun insertRecommendedMoviesToDB(movieId: Int, movies: List<Movie>) {
        movies.forEach { movie ->
            recommendedMovieDao.insertRecommendedMovie(movie.mapToDataBaseRecommendedMovie(movieId))
        }
    }

    override suspend fun getDBGetRecommendedMoviesById(movieId: Int): CoroutineResult<List<Movie>> =
        recommendedMovieDao.getDBRecommendedMoviesById(movieId).let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.mapToLocalRecommendedMovieList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }
}
