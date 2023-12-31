package com.georfernandez.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.georfernandez.data.database.entity.TopRatedMovieEntity
import com.georfernandez.data.database.entity.TopRatedMovieEntityComplete

@Dao
interface TopRatedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMovie(topRatedMovieEntity: TopRatedMovieEntity)

    @Transaction
    @Query("SELECT * FROM top_rated_movie")
    fun getDBTopRatedMovies(): List<TopRatedMovieEntityComplete>
}
