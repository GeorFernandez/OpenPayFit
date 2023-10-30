package com.georfernandez.openpayfit.di

import com.georfernandez.di.ApiModule
import com.georfernandez.di.DataBaseModule
import com.georfernandez.di.ServiceModule
import com.georfernandez.di.UseCaseModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ApiModule::class,
        DataBaseModule::class,
        ServiceModule::class,
        UseCaseModule::class,
    ],
)
@InstallIn(SingletonComponent::class)
class AppModule
