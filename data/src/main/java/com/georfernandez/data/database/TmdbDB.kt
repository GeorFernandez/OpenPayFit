package com.georfernandez.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.georfernandez.data.database.dao.ActorDao
import com.georfernandez.data.database.dao.MovieStarringDao
import com.georfernandez.data.database.dao.PopularMovieDao
import com.georfernandez.data.database.dao.RecommendedMovieDao
import com.georfernandez.data.database.dao.TopRatedMovieDao
import com.georfernandez.data.database.entity.ActorEntity
import com.georfernandez.data.database.entity.MovieEntity
import com.georfernandez.data.database.entity.MovieStarringEntity
import com.georfernandez.data.database.entity.PopularMovieEntity
import com.georfernandez.data.database.entity.RecommendedMovieEntity
import com.georfernandez.data.database.entity.TopRatedMovieEntity

@Database(
    entities = [
        ActorEntity::class,
        MovieStarringEntity::class,
        MovieEntity::class,
        PopularMovieEntity::class,
        TopRatedMovieEntity::class,
        RecommendedMovieEntity::class,
    ],
    version = 1,
)
abstract
class TmdbDB : RoomDatabase() {
    abstract fun actorDao(): ActorDao
    abstract fun movieStarringDao(): MovieStarringDao
    abstract fun popularMovieDao(): PopularMovieDao
    abstract fun topRatedMovieDao(): TopRatedMovieDao
    abstract fun recommendedMovieDao(): RecommendedMovieDao
}
