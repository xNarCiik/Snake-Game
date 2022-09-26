package com.dms.snake.feature_game.domain.repository

interface GameRepository {

    fun getBestScore(): Int

    fun saveBestScore(bestScore: Int)

}