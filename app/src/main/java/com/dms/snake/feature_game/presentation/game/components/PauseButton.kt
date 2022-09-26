package com.dms.snake.feature_game.presentation.game.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import com.dms.snake.feature_game.presentation.common.components.SnakeButtonIcon

@Composable
fun PauseButton(paused: Boolean, onPauseClick: () -> Unit, onResumeClick: () -> Unit) {
    SnakeButtonIcon(
        imageVector = if (paused) Icons.Rounded.PlayArrow else Icons.Rounded.Pause,
        contentDescription = "pause"
    )
    {
        if (paused)
            onResumeClick()
        else
            onPauseClick()
    }
}