package com.georfernandez.data.mapper

import com.georfernandez.data.database.entity.MovieEntity
import com.georfernandez.data.database.entity.PopularMovieEntity
import com.georfernandez.data.database.entity.PopularMovieEntityComplete
import com.georfernandez.data.database.entity.RecommendedMovieEntity
import com.georfernandez.data.database.entity.RecommendedMovieEntityComplete
import com.georfernandez.data.database.entity.TopRatedMovieEntity
import com.georfernandez.data.database.entity.TopRatedMovieEntityComplete
import com.georfernandez.data.service.response.MoviePagingResponse
import com.georfernandez.data.service.response.MovieResponse
import com.georfernandez.domain.entity.Movie

fun MoviePagingResponse.mapToLocalMovieList(): List<Movie> = results.map { it.mapToLocalMovie() }
private fun MovieResponse.mapToLocalMovie() =
    Movie(
        id = id,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )

fun Movie.mapToDataBasePopularMovie() =
    PopularMovieEntity(
        popularMovieId = id,
        movieEntity = MovieEntity(
            id = id,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
        ),
    )

fun List<PopularMovieEntityComplete>.mapToLocalPopularMovieList(): List<Movie> = this.map { it.mapToLocalMovie() }
private fun PopularMovieEntityComplete.mapToLocalMovie() =
    Movie(
        id = popularMovieEntity.popularMovieId,
        overview = movieEntity.overview,
        popularity = movieEntity.popularity,
        posterPath = movieEntity.posterPath,
        title = movieEntity.title,
        video = movieEntity.video,
        voteAverage = movieEntity.voteAverage,
        voteCount = movieEntity.voteCount,
    )

fun Movie.mapToDataBaseTopRatedMovie() =
    TopRatedMovieEntity(
        topRatedMovieId = id,
        movieEntity = MovieEntity(
            id = id,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
        ),
    )

fun List<TopRatedMovieEntityComplete>.mapToLocalTopRatedMovieList(): List<Movie> = this.map { it.mapToLocalMovie() }
private fun TopRatedMovieEntityComplete.mapToLocalMovie() =
    Movie(
        id = topRatedMovieEntity.topRatedMovieId,
        overview = movieEntity.overview,
        popularity = movieEntity.popularity,
        posterPath = movieEntity.posterPath,
        title = movieEntity.title,
        video = movieEntity.video,
        voteAverage = movieEntity.voteAverage,
        voteCount = movieEntity.voteCount,
    )

fun Movie.mapToDataBaseRecommendedMovie(movieId: Int) =
    RecommendedMovieEntity(
        recommendedMovieId = id,
        originalMovieId = movieId,
        movieEntity = MovieEntity(
            id = id,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
        ),
    )

fun List<RecommendedMovieEntityComplete>.mapToLocalRecommendedMovieList(): List<Movie> = this.map { it.mapToLocalMovie() }
private fun RecommendedMovieEntityComplete.mapToLocalMovie() =
    Movie(
        id = recommendedMovieEntity.recommendedMovieId,
        overview = movieEntity.overview,
        popularity = movieEntity.popularity,
        posterPath = movieEntity.posterPath,
        title = movieEntity.title,
        video = movieEntity.video,
        voteAverage = movieEntity.voteAverage,
        voteCount = movieEntity.voteCount,
    )
