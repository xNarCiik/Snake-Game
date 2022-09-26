package com.dms.snake.di

import android.app.Application
import com.dms.snake.feature_game.data.repository.GameRepositoryImpl
import com.dms.snake.feature_game.domain.repository.GameRepository
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