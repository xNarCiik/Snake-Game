package com.dms.snake.di

import android.app.Application
import com.dms.snake.features.game.data.repository.GameRepositoryImpl
import com.dms.snake.features.game.domain.repository.GameRepository
import com.dms.snake.features.game.domain.use_case.IsAllowToUpdateOrientationUseCase
import com.dms.snake.features.game.domain.use_case.GameUseCases
import com.dms.snake.features.game.domain.use_case.GenerateFoodStateUseCase
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

    @Provides
    @Singleton
    fun provideGameUseCases(): GameUseCases =
        GameUseCases(
            isAllowToUpdateOrientation = IsAllowToUpdateOrientationUseCase(),
            generateFoodState = GenerateFoodStateUseCase()
        )

}