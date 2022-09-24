package com.example.sampleapp.di

import android.app.Application
import com.example.sampleapp.feature_game.data.repository.GameRepositoryImpl
import com.example.sampleapp.feature_game.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGameRepository(app: Application): GameRepository =
        GameRepositoryImpl(app.applicationContext)

}