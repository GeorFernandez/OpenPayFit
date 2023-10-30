package com.georfernandez.data.mapper

import com.georfernandez.data.database.entity.ActorEntity
import com.georfernandez.data.database.entity.ActorEntityWithKnownForEntity
import com.georfernandez.data.database.entity.MovieStarringEntity
import com.georfernandez.data.service.response.ActorPagingResponse
import com.georfernandez.data.service.response.ActorResponse
import com.georfernandez.data.service.response.MovieStarringResponse
import com.georfernandez.domain.entity.Actor
import com.georfernandez.domain.entity.MovieStarring
import com.georfernandez.domain.utils.NO_BIOGRAPHY

fun ActorPagingResponse.mapToLocalActorList(): List<Actor> = results.map { it.mapToLocalActor() }
private fun ActorResponse.mapToLocalActor() =
    Actor(
        id = id,
        movieStarringDepartment = movieStarringDepartment,
        movieStarring = movieStarring.map { it.mapToLocalMovieStarring() },
        name = name,
        popularity = popularity,
        profilePath = profilePath,
        biography = biography.ifEmpty {
            NO_BIOGRAPHY
        },
    )

private fun MovieStarringResponse.mapToLocalMovieStarring() =
    MovieStarring(
        id = id,
        mediaType = mediaType,
        name = name,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        title = title,
        video = video,
    )

fun Actor.mapToDataBaseActor() =
    ActorEntity(
        id = id,
        movieStarringDepartment = movieStarringDepartment,
        name = name,
        popularity = popularity,
        profilePath = profilePath,
        biography = biography,
    )

fun MovieStarring.mapToDataBaseKnownFor(userId: Int) =
    MovieStarringEntity(
        id = id,
        userId = userId,
        mediaType = mediaType,
        name = name,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        title = title,
        video = video,
    )

fun List<ActorEntityWithKnownForEntity>.mapToLocalActorList(): List<Actor> = this.map { it.mapToLocalActor() }
private fun ActorEntityWithKnownForEntity.mapToLocalActor() =
    Actor(
        id = actorEntity.id,
        movieStarringDepartment = actorEntity.movieStarringDepartment,
        movieStarring = movieStarringEntity.map { it.mapToLocalMovieStarring() },
        name = actorEntity.name,
        popularity = actorEntity.popularity,
        profilePath = actorEntity.profilePath,
        biography = actorEntity.biography,
    )

private fun MovieStarringEntity.mapToLocalMovieStarring() =
    MovieStarring(
        id = id,
        mediaType = mediaType,
        name = name,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        title = title,
        video = video,
    )
