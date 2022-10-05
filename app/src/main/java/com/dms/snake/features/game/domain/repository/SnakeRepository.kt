package com.dms.snake.features.game.domain.repository

import com.dms.snake.features.game.domain.model.Score
import kotlinx.coroutines.flow.Flow

interface SnakeRepository {

    fun getScores(): Flow<List<Score>>

    suspend fun saveScore(score: Score)

    fun musicIsMuted(): Boolean

    fun saveMusicIsMuted(muted: Boolean)
}