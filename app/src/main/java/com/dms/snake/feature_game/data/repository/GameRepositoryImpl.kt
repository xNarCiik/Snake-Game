package com.dms.snake.feature_game.data.repository

import android.content.Context
import com.dms.snake.feature_game.domain.repository.GameRepository

class GameRepositoryImpl(
    context: Context
) : GameRepository {

    private val sharedPreferences =
        context.getSharedPreferences(gameSharedPref, Context.MODE_PRIVATE)

    override fun getBestScore(): Int = sharedPreferences.getInt(bestScorePref, 0)

    override fun saveBestScore(bestScore: Int) =
        sharedPreferences.edit().putInt(bestScorePref, bestScore).apply()

    private companion object {
        const val gameSharedPref = "game_shared_pref"
        const val bestScorePref = "best_score_pref"
    }

}