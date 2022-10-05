package com.dms.snake.features.game.presentation.common

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dms.snake.features.game.domain.service.BackgroundMusicService
import com.dms.snake.features.game.domain.repository.SnakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val application: Application,
    private val snakeRepository: SnakeRepository
) : ViewModel() {

    var isMuted by mutableStateOf(snakeRepository.musicIsMuted())
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
        snakeRepository.saveMusicIsMuted(isMuted)
    }

    private fun startServiceMusic(intent: Intent) = application.startService(intent)
}