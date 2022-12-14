package com.dms.snake.features.game.domain.service

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import com.dms.snake.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player.REPEAT_MODE_ALL

class BackgroundMusicService : Service() {

    private lateinit var musicPlayer: ExoPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        initializeMusicPlayer()
    }

    private fun initializeMusicPlayer() {
        musicPlayer = ExoPlayer.Builder(this).build().apply {
            val uri = Uri.parse("android.resource://$packageName/${R.raw.background_music}")
            val mediaItem = MediaItem.fromUri(uri)
            setMediaItem(mediaItem)
            repeatMode = REPEAT_MODE_ALL
            prepare()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.apply {
            action?.let {
                when (it) {
                    UNMUTE_MUSIC_ACTION -> musicPlayer.volume = 1f
                    MUTE_MUSIC_ACTION -> musicPlayer.volume = 0f
                    PLAY_MUSIC_ACTION -> musicPlayer.play()
                    PAUSE_MUSIC_ACTION -> musicPlayer.pause()
                    STOP_MUSIC_ACTION -> musicPlayer.stop()
                }
            }
            // Used only at launch
            extras?.getBoolean(EXTRA_IS_MUTED)?.let {
                if (it) musicPlayer.volume = 0f
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.stop()
    }

    companion object {
        const val UNMUTE_MUSIC_ACTION = "BACKGROUND_MUSIC_SERVICE.UNMUTE_MUSIC_ACTION"
        const val MUTE_MUSIC_ACTION = "BACKGROUND_MUSIC_SERVICE.MUTE_MUSIC_ACTION"
        const val PLAY_MUSIC_ACTION = "BACKGROUND_MUSIC_SERVICE.PLAY_MUSIC_ACTION"
        const val PAUSE_MUSIC_ACTION = "BACKGROUND_MUSIC_SERVICE.PAUSE_MUSIC_ACTION"
        const val STOP_MUSIC_ACTION = "BACKGROUND_MUSIC_SERVICE.STOP_MUSIC_ACTION"
        const val EXTRA_IS_MUTED = "is_muted"
    }
}