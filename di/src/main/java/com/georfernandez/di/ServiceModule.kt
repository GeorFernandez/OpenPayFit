package com.georfernandez.di

import com.georfernandez.data.service.ActorServiceImpl
import com.georfernandez.data.service.api.TMDBApi
import com.georfernandez.domain.service.ActorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun provideActorService(tMDBApi: TMDBApi): ActorService = ActorServiceImpl(tMDBApi)
}
