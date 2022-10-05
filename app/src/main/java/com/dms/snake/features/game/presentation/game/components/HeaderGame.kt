package com.dms.snake.features.game.presentation.game.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dms.snake.R
import com.dms.snake.features.game.presentation.game.GameEvent
import com.dms.snake.features.game.presentation.game.GameViewModel
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun HeaderGame(
    gameViewModel: GameViewModel
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.score),
                style = MaterialTheme.typography.h3,
                color = GreenSnake
            )
            Text(
                text = gameViewModel.currentScore.toString(),
                style = MaterialTheme.typography.h3,
                color = GreenSnake
            )
        }

        PauseButton {
            gameViewModel.onEvent(GameEvent.Pause(true))
        }
    }
}

@Composable
@Preview
fun HeaderGamePreview() {
    HeaderGame(
        gameViewModel = hiltViewModel()
    )
}