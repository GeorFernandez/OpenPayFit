package com.georfernandez.di

import com.georfernandez.domain.database.TMDBRepository
import com.georfernandez.domain.service.FirebaseService
import com.georfernandez.domain.service.TMDBService
import com.georfernandez.domain.usecases.GetMostPopularActorUseCase
import com.georfernandez.domain.usecases.GetMostPopularActorUseCaseImpl
import com.georfernandez.domain.usecases.GetMostPopularMoviesUseCase
import com.georfernandez.domain.usecases.GetMostPopularMoviesUseCaseImpl
import com.georfernandez.domain.usecases.GetRecommendedMoviesByIdUseCase
import com.georfernandez.domain.usecases.GetRecommendedMoviesByIdUseCaseImpl
import com.georfernandez.domain.usecases.GetTopRatedMoviesUseCase
import com.georfernandez.domain.usecases.GetTopRatedMoviesUseCaseImpl
import com.georfernandez.domain.usecases.SavePicturesUseCase
import com.georfernandez.domain.usecases.SavePicturesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetMostPopularActorUseCase(
        tMDBService: TMDBService,
        tMDBRepository: TMDBRepository,
    ): GetMostPopularActorUseCase =
        GetMostPopularActorUseCaseImpl(tMDBService, tMDBRepository)

    @Provides
    fun provideGetPopularMoviesUseCase(
        tMDBService: TMDBService,
        tMDBRepository: TMDBRepository,
    ): GetMostPopularMoviesUseCase =
        GetMostPopularMoviesUseCaseImpl(tMDBService, tMDBRepository)

    @Provides
    fun provideGetTopRatedMoviesUseCase(
        tMDBService: TMDBService,
        tMDBRepository: TMDBRepository,
    ): GetTopRatedMoviesUseCase =
        GetTopRatedMoviesUseCaseImpl(tMDBService, tMDBRepository)

    @Provides
    fun provideGetRecommendedMoviesByIdUseCase(
        tMDBService: TMDBService,
        tMDBRepository: TMDBRepository,
    ): GetRecommendedMoviesByIdUseCase =
        GetRecommendedMoviesByIdUseCaseImpl(tMDBService, tMDBRepository)

    @Provides
    fun provideSaveImagesUseCase(firebaseService: FirebaseService): SavePicturesUseCase = SavePicturesUseCaseImpl(firebaseService)
}
