package com.georfernandez.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.georfernandez.data.database.dao.ActorDao
import com.georfernandez.data.database.dao.MovieStarringDao
import com.georfernandez.data.database.entity.ActorEntity
import com.georfernandez.data.database.entity.MovieStarringEntity

@Database(
    entities = [
        ActorEntity::class,
        MovieStarringEntity::class,
    ],
    version = 1,
)
abstract class ActorDB : RoomDatabase() {
    abstract fun actorDao(): ActorDao
    abstract fun movieStarringDao(): MovieStarringDao
}
