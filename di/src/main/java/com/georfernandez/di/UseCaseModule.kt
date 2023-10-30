package com.georfernandez.di

import com.georfernandez.domain.database.ActorRepository
import com.georfernandez.domain.service.ActorService
import com.georfernandez.domain.usecases.GetMostPopularActorUseCase
import com.georfernandez.domain.usecases.GetMostPopularActorUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetMostPopularActorUseCase(
        actorService: ActorService,
        actorRepository: ActorRepository,
    ): GetMostPopularActorUseCase =
        GetMostPopularActorUseCaseImpl(actorService, actorRepository)
}
