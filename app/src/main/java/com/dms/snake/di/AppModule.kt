package com.dms.snake.di

import android.app.Application
import androidx.room.Room
import com.dms.snake.features.game.data.data_source.SnakeDatabase
import com.dms.snake.features.game.data.repository.SnakeRepositoryImpl
import com.dms.snake.features.game.domain.repository.SnakeRepository
import com.dms.snake.features.game.domain.use_case.game.CalculateNextFirstPointSnakeUseCase
import com.dms.snake.features.game.domain.use_case.game.GameUseCases
import com.dms.snake.features.game.domain.use_case.game.GenerateFoodStateUseCase
import com.dms.snake.features.game.domain.use_case.game.IsAllowToUpdateOrientationUseCase
import com.dms.snake.features.game.domain.use_case.scores.GetScoresUseCase
import com.dms.snake.features.game.domain.use_case.scores.InsertScoreUseCase
import com.dms.snake.features.game.domain.use_case.scores.ScoresUseCases
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
    fun provideSnakeDatabase(app: Application): SnakeDatabase {
        return Room.databaseBuilder(
            app,
            SnakeDatabase::class.java,
            SnakeDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameRepository(app: Application, snakeDatabase: SnakeDatabase): SnakeRepository =
        SnakeRepositoryImpl(app.applicationContext, snakeDatabase.scoreDao)

    @Provides
    @Singleton
    fun provideGameUseCases(): GameUseCases =
        GameUseCases(
            isAllowToUpdateOrientation = IsAllowToUpdateOrientationUseCase(),
            generateFoodState = GenerateFoodStateUseCase(),
            calculateNextFirstPointSnake = CalculateNextFirstPointSnakeUseCase()
        )

    @Provides
    @Singleton
    fun provideScoresUseCases(snakeRepository: SnakeRepository): ScoresUseCases =
        ScoresUseCases(
            getScores = GetScoresUseCase(snakeRepository = snakeRepository),
            insertScore = InsertScoreUseCase(snakeRepository = snakeRepository)
        )

}