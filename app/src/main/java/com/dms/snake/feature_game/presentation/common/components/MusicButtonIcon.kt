package com.dms.snake.feature_game.presentation.common.components

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeMute
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import com.dms.snake.BackgroundMusicService

@Composable
fun MusicButtonIcon(musicIsMuted: MutableState<Boolean>){
    val context = LocalContext.current

    SnakeButtonIcon(
        imageVector = if (musicIsMuted.value) Icons.Rounded.VolumeMute else Icons.Rounded.VolumeUp,
        contentDescription = "volume"
    ) {
        context.startService(Intent(context, BackgroundMusicService::class.java).apply {
            action =
                if (musicIsMuted.value) BackgroundMusicService.UNMUTE_MUSIC_ACTION else BackgroundMusicService.MUTE_MUSIC_ACTION
            musicIsMuted.value = !musicIsMuted.value
        })
    }
}