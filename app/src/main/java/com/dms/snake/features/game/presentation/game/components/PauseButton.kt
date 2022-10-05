package com.dms.snake.features.game.presentation.game.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.runtime.Composable
import com.dms.snake.features.game.presentation.common.components.SnakeButtonIcon

@Composable
fun PauseButton(onPauseClick: () -> Unit) {
    SnakeButtonIcon(
        imageVector = Icons.Rounded.Pause,
        contentDescription = "pause"
    )
    {
        onPauseClick()
    }
}