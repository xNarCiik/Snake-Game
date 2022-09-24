package com.example.sampleapp.feature_game.domain.repository

interface GameRepository {

    fun getBestScore(): Int

    fun saveBestScore(bestScore: Int)

}