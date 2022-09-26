package com.dms.snake.feature_game.presentation.game.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun PauseButton(paused: Boolean, onPauseClick: () -> Unit, onResumeClick: () -> Unit) {
    Icon(modifier = Modifier
        .clickable {
            if (paused)
                onResumeClick()
            else
                onPauseClick()
        }
        .then(Modifier.size(40.dp))
        .border(1.dp, GreenSnake, shape = CircleShape),
        imageVector = if (paused) Icons.Rounded.PlayArrow else Icons.Rounded.Pause,
        contentDescription = "pause",
        tint = GreenSnake)

}