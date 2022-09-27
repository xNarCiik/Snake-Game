package com.dms.snake.feature_game.domain.repository

interface GameRepository {

    fun getHighscores(): Int

    fun saveScore(name: String, score: Int)

    fun musicIsMuted(): Boolean

    fun saveMusicIsMuted(muted: Boolean)
}