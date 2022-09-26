package com.dms.snake.feature_game.presentation.game.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dms.snake.R
import com.dms.snake.feature_game.presentation.game.GameEvent
import com.dms.snake.feature_game.presentation.game.GameViewModel
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun HeaderGame(
    navController: NavController,
    gameViewModel: GameViewModel
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .then(Modifier.size(40.dp))
                .border(1.dp, GreenSnake, shape = CircleShape),
            imageVector = Icons.Rounded.Home,
            contentDescription = "home",
            tint = GreenSnake
        )

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

        PauseButton(gameViewModel.paused, onPauseClick = {
            gameViewModel.onEvent(GameEvent.Pause(true))
        }, onResumeClick = {
            gameViewModel.onEvent(GameEvent.Pause(false))
        })
    }
}