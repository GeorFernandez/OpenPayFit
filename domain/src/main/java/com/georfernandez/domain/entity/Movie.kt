package com.georfernandez.domain.entity

data class Movie(
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
)
