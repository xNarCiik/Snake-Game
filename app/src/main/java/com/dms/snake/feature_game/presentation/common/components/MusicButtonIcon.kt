package com.dms.snake.feature_game.presentation.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeMute
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.Composable
import com.dms.snake.feature_game.presentation.common.MusicViewModel

@Composable
fun MusicButtonIcon(musicViewModel : MusicViewModel){
    SnakeButtonIcon(
        imageVector = if (musicViewModel.isMuted) Icons.Rounded.VolumeMute else Icons.Rounded.VolumeUp,
        contentDescription = "volume"
    ) {
        musicViewModel.onMusicButtonClicked()
    }
}