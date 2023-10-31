package com.georfernandez.di

import android.content.Context
import androidx.room.Room
import com.georfernandez.data.database.TMDBRepositoryImpl
import com.georfernandez.data.database.TmdbDB
import com.georfernandez.data.database.dao.ActorDao
import com.georfernandez.data.database.dao.MovieStarringDao
import com.georfernandez.data.database.dao.PopularMovieDao
import com.georfernandez.data.database.dao.RecommendedMovieDao
import com.georfernandez.data.database.dao.TopRatedMovieDao
import com.georfernandez.domain.database.TMDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    private const val DB = "ActorRepository"

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): TmdbDB {
        return Room
            .databaseBuilder(context, TmdbDB::class.java, DB)
            .build()
    }

    @Provides
    fun provideActorDao(tMDBDB: TmdbDB): ActorDao = tMDBDB.actorDao()

    @Provides
    fun provideMovieStarringDao(tMDBDB: TmdbDB): MovieStarringDao = tMDBDB.movieStarringDao()

    @Provides
    @Singleton
    fun providePopularMovieDao(tMDBDB: TmdbDB): PopularMovieDao = tMDBDB.popularMovieDao()

    @Provides
    @Singleton
    fun provideTopRatedMovieDao(tMDBDB: TmdbDB): TopRatedMovieDao = tMDBDB.topRatedMovieDao()

    @Provides
    @Singleton
    fun provideRecommendedMovieDao(tMDBDB: TmdbDB): RecommendedMovieDao = tMDBDB.recommendedMovieDao()

    @Provides
    fun provideTheMovieDBRepository(
        actorDao: ActorDao,
        movieStarringDao: MovieStarringDao,
        popularMovieDao: PopularMovieDao,
        topRatedMovieDao: TopRatedMovieDao,
        recommendedMovieDao: RecommendedMovieDao,
    ): TMDBRepository =
        TMDBRepositoryImpl(
            actorDao = actorDao,
            movieStarringDao = movieStarringDao,
            popularMovieDao = popularMovieDao,
            topRatedMovieDao = topRatedMovieDao,
            recommendedMovieDao = recommendedMovieDao,
        )
}
