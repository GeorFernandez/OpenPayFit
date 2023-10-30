package com.georfernandez.domain.entity

data class Actor(
    val id: Int,
    val movieStarring: List<MovieStarring>,
    val movieStarringDepartment: String,
    val name: String,
    val popularity: Double,
    val biography: String,
    val profilePath: String,
)

data class MovieStarring(
    val id: Int,
    val mediaType: String,
    val name: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val title: String,
    val video: Boolean,
)
