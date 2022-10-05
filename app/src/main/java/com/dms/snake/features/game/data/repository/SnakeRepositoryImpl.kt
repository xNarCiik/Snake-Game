package com.dms.snake.features.game.data.repository

import android.content.Context
import com.dms.snake.features.game.data.data_source.ScoreDao
import com.dms.snake.features.game.domain.model.Score
import com.dms.snake.features.game.domain.repository.SnakeRepository
import kotlinx.coroutines.flow.Flow

class SnakeRepositoryImpl(
    context: Context,
    private val scoreDao: ScoreDao
) : SnakeRepository {

    private val sharedPreferences =
        context.getSharedPreferences(gameSharedPref, Context.MODE_PRIVATE)

    override fun getScores(): Flow<List<Score>> = scoreDao.getScores()

    override suspend fun saveScore(score: Score) {
        scoreDao.insertScore(score)
    }

    override fun musicIsMuted(): Boolean = sharedPreferences.getBoolean(musicIsMuted, false)

    override fun saveMusicIsMuted(muted: Boolean) =
        sharedPreferences.edit().putBoolean(musicIsMuted, muted).apply()


    private companion object {
        const val gameSharedPref = "game_shared_pref"
        const val musicIsMuted = "music_is_muted_pref"
    }

}