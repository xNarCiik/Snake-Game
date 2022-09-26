package com.dms.snake

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player.REPEAT_MODE_ALL

// TODO where put service ?
class BackgroundMusicService : Service() {

    private val musicPlayer by lazy {
        ExoPlayer.Builder(this).build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        initializeMusicPlayer()
    }

    private fun initializeMusicPlayer() {
        musicPlayer.apply {
            val uri = Uri.parse("android.resource://$packageName/${R.raw.background_music}")
            val mediaItem = MediaItem.fromUri(uri)
            setMediaItem(mediaItem)
            repeatMode = REPEAT_MODE_ALL
            prepare()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        musicPlayer.play()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.stop()
    }

}