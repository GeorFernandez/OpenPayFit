package com.georfernandez.data.service.response

import com.georfernandez.domain.utils.EMPTY_STRING
import com.georfernandez.domain.utils.ZERO
import com.georfernandez.domain.utils.ZERO_DOUBLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePagingResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<MovieResponse>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
) : java.io.Serializable

@Serializable
data class MovieResponse(
    @SerialName("id") val id: Int,
    @SerialName("overview") val overview: String = EMPTY_STRING,
    @SerialName("popularity") val popularity: Double = ZERO_DOUBLE,
    @SerialName("poster_path") val posterPath: String = EMPTY_STRING,
    @SerialName("title") val title: String = EMPTY_STRING,
    @SerialName("video") val video: Boolean = false,
    @SerialName("vote_average") val voteAverage: Double = ZERO_DOUBLE,
    @SerialName("vote_count") val voteCount: Int = ZERO
) : java.io.Serializable
