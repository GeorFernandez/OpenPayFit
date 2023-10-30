package com.georfernandez.data.service.response

import com.georfernandez.domain.utils.EMPTY_STRING
import com.georfernandez.domain.utils.ZERO_DOUBLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorPagingResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<ActorResponse>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int,
) : java.io.Serializable

@Serializable
data class ActorResponse(
    @SerialName("id") val id: Int,
    @SerialName("known_for_department") val movieStarringDepartment: String = EMPTY_STRING,
    @SerialName("known_for") val movieStarring: List<MovieStarringResponse> = emptyList(),
    @SerialName("name") val name: String = EMPTY_STRING,
    @SerialName("popularity") val popularity: Double = ZERO_DOUBLE,
    @SerialName("profile_path") val profilePath: String = EMPTY_STRING,
    @SerialName("biography") val biography: String = EMPTY_STRING,
) : java.io.Serializable

@Serializable
data class MovieStarringResponse(
    @SerialName("id") val id: Int,
    @SerialName("media_type") val mediaType: String = EMPTY_STRING,
    @SerialName("name") val name: String = EMPTY_STRING,
    @SerialName("original_title") val originalTitle: String = EMPTY_STRING,
    @SerialName("overview") val overview: String = EMPTY_STRING,
    @SerialName("popularity") val popularity: Double = ZERO_DOUBLE,
    @SerialName("poster_path") val posterPath: String = EMPTY_STRING,
    @SerialName("title") val title: String = EMPTY_STRING,
    @SerialName("video") val video: Boolean = false,
) : java.io.Serializable
