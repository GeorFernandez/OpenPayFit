package com.georfernandez.di

import android.content.Context
import androidx.room.Room
import com.georfernandez.data.database.ActorDB
import com.georfernandez.data.database.ActorRepositoryImpl
import com.georfernandez.data.database.dao.ActorDao
import com.georfernandez.data.database.dao.MovieStarringDao
import com.georfernandez.domain.database.ActorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    private const val DB = "ActorRepository"

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ActorDB {
        return Room
            .databaseBuilder(context, ActorDB::class.java, DB)
            .build()
    }

    @Provides
    fun provideActorDao(actorDB: ActorDB): ActorDao = actorDB.actorDao()

    @Provides
    fun provideMovieStarringDao(actorDB: ActorDB): MovieStarringDao = actorDB.movieStarringDao()

    @Provides
    fun provideTheMovieDBRepository(
        actorDao: ActorDao,
        movieStarringDao: MovieStarringDao,
    ): ActorRepository =
        ActorRepositoryImpl(
            actorDao = actorDao,
            movieStarringDao = movieStarringDao,
        )
}
