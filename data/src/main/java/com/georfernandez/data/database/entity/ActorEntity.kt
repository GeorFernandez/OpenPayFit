package com.georfernandez.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "actor")
class ActorEntity(
    @PrimaryKey val id: Int,
    val movieStarringDepartment: String,
    val name: String,
    val popularity: Double,
    val biography: String,
    val profilePath: String,
)

@Entity(
    tableName = "movie_starring",
    foreignKeys = [
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class MovieStarringEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val mediaType: String,
    val name: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val title: String,
    val video: Boolean,
)

data class ActorEntityWithKnownForEntity(
    @Embedded val actorEntity: ActorEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId",
    )
    val movieStarringEntity: List<MovieStarringEntity>,
)
