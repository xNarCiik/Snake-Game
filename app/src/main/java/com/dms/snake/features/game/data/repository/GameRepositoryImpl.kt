package com.dms.snake.features.game.data.repository

import android.content.Context
import com.dms.snake.features.game.domain.repository.GameRepository

class GameRepositoryImpl(
    context: Context
) : GameRepository {

    private val sharedPreferences =
        context.getSharedPreferences(gameSharedPref, Context.MODE_PRIVATE)

    override fun getHighscores(): Int = sharedPreferences.getInt(bestScorePref, 0)

    override fun saveScore(name: String, score: Int) =
        sharedPreferences.edit().putInt(bestScorePref, score).apply()

    override fun musicIsMuted(): Boolean = sharedPreferences.getBoolean(musicIsMuted, false)

    override fun saveMusicIsMuted(muted: Boolean) =
        sharedPreferences.edit().putBoolean(musicIsMuted, muted).apply()


    private companion object {
        const val gameSharedPref = "game_shared_pref"
        const val bestScorePref = "best_score_pref"
        const val musicIsMuted = "music_is_muted_pref"
    }


}