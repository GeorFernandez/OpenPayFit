package com.georfernandez.di

import com.georfernandez.data.service.FirebaseServiceImpl
import com.georfernandez.data.service.TMDBServiceImpl
import com.georfernandez.data.service.api.TMDBApi
import com.georfernandez.domain.service.FirebaseService
import com.georfernandez.domain.service.TMDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun provideTMDBService(tMDBApi: TMDBApi): TMDBService = TMDBServiceImpl(tMDBApi)

    @Provides
    fun provideFirebaseService(): FirebaseService = FirebaseServiceImpl()
}
