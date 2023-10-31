package com.georfernandez.data.service.api

import com.georfernandez.data.service.response.ActorPagingResponse
import com.georfernandez.data.service.response.MoviePagingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBApi {
    @GET("3/person/popular")
    fun getPopularActors(): Call<ActorPagingResponse>

    @GET("/3/movie/popular")
    fun getPopularMovieList(): Call<MoviePagingResponse>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovieList(): Call<MoviePagingResponse>

    @GET("/3/movie/{movie_id}/recommendations")
    fun getRecommendedMoviesListById(@Path("movie_id") movieId: Int): Call<MoviePagingResponse>
}
