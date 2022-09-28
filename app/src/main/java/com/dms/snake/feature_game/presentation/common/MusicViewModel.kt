package com.dms.snake.feature_game.presentation.common

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dms.snake.BackgroundMusicService
import com.dms.snake.feature_game.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val application: Application,
    private val gameRepository: GameRepository
) : ViewModel() {

    var isMuted by mutableStateOf(gameRepository.musicIsMuted())
        private set

    fun init() {
        startServiceMusic(Intent(application, BackgroundMusicService::class.java).apply {
            action = BackgroundMusicService.PLAY_MUSIC_ACTION
            putExtra(BackgroundMusicService.EXTRA_IS_MUTED, isMuted)
        })
    }

    fun onMusicButtonClicked() {
        startServiceMusic(Intent(application, BackgroundMusicService::class.java).apply {
            action =
                if (isMuted) BackgroundMusicService.UNMUTE_MUSIC_ACTION else BackgroundMusicService.MUTE_MUSIC_ACTION
        })
        this.isMuted = !isMuted
        gameRepository.saveMusicIsMuted(isMuted)
    }

    private fun startServiceMusic(intent: Intent) = application.startService(intent)
}