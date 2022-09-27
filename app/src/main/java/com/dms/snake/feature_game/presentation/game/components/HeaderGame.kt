package com.dms.snake.feature_game.presentation.game.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.R
import com.dms.snake.feature_game.presentation.common.MusicViewModel
import com.dms.snake.feature_game.presentation.common.components.MusicButtonIcon
import com.dms.snake.feature_game.presentation.common.components.SnakeButtonIcon
import com.dms.snake.feature_game.presentation.game.GameEvent
import com.dms.snake.feature_game.presentation.game.GameViewModel
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun HeaderGame(
    navController: NavController,
    musicViewModel: MusicViewModel,
    gameViewModel: GameViewModel
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SnakeButtonIcon(imageVector = Icons.Rounded.Home, contentDescription = "home")
        {
            navController.popBackStack()
        }

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

        MusicButtonIcon(musicViewModel = musicViewModel)

        PauseButton(gameViewModel.paused, onPauseClick = {
            gameViewModel.onEvent(GameEvent.Pause(true))
        }, onResumeClick = {
            gameViewModel.onEvent(GameEvent.Pause(false))
        })
    }
}

@Composable
@Preview
fun HeaderGamePreview() {
    HeaderGame(navController = rememberNavController(), musicViewModel = hiltViewModel(), gameViewModel = hiltViewModel())
}