package com.georfernandez.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.georfernandez.data.database.entity.ActorEntity
import com.georfernandez.data.database.entity.ActorEntityWithKnownForEntity
import com.georfernandez.data.database.entity.MovieStarringEntity

@Dao
interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActor(actorEntity: ActorEntity)

    @Transaction
    @Query("SELECT * FROM actor")
    fun getDBActor(): List<ActorEntityWithKnownForEntity>
}

@Dao
interface MovieStarringDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieStarring(movieStarringEntity: MovieStarringEntity)
}
