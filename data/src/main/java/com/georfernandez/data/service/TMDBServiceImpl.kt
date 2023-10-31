package com.georfernandez.data.service

import com.georfernandez.data.mapper.mapToLocalActorList
import com.georfernandez.data.mapper.mapToLocalMovieList
import com.georfernandez.data.service.api.TMDBApi
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.entity.Movie
import com.georfernandez.domain.service.TMDBService
import com.georfernandez.domain.utils.CoroutineResult
import javax.inject.Inject

class TMDBServiceImpl @Inject constructor(private val TMDBApi: TMDBApi) : TMDBService {
    override suspend fun getMostPopularActors(): CoroutineResult<List<Actor>> {
        try {
            val callResponse = TMDBApi.getPopularActors()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it.mapToLocalActorList())
                }
            }
        } catch (e: Exception) {
            return CoroutineResult.Failure(e)
        }
        return CoroutineResult.Failure(Exception())
    }

    override suspend fun getPopularMovies(): CoroutineResult<List<Movie>> {
        try {
            val callResponse = TMDBApi.getPopularMovieList()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it.mapToLocalMovieList())
                }
            }
        } catch (e: Exception) {
            return CoroutineResult.Failure(e)
        }
        return CoroutineResult.Failure(Exception())
    }

    override suspend fun getRecommendedMoviesById(tMDBMovieId: Int): CoroutineResult<List<Movie>> {
        try {
            val callResponse = TMDBApi.getRecommendedMoviesListById(tMDBMovieId)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it.mapToLocalMovieList())
                }
            }
        } catch (e: Exception) {
            return CoroutineResult.Failure(e)
        }
        return CoroutineResult.Failure(Exception())
    }
    override suspend fun getTopRatedMovies(): CoroutineResult<List<Movie>> {
        try {
            val callResponse = TMDBApi.getTopRatedMovieList()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it.mapToLocalMovieList())
                }
            }
        } catch (e: Exception) {
            return CoroutineResult.Failure(Exception())
        }
        return CoroutineResult.Failure(Exception())
    }
}
