package com.dms.snake.features.game.presentation.game.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.R
import com.dms.snake.features.game.presentation.common.MusicViewModel
import com.dms.snake.features.game.presentation.common.components.MusicButtonIcon
import com.dms.snake.features.game.presentation.common.components.SnakeButtonIcon
import com.dms.snake.features.game.presentation.common.components.SnakeDialog
import com.dms.snake.features.game.presentation.game.GameEvent
import com.dms.snake.features.game.presentation.game.GameViewModel
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun PauseDialog(
    navController: NavController,
    gameViewModel: GameViewModel,
    musicViewModel: MusicViewModel
) {
    // Use small trick : the dialog will not disappear directly when we do a popBackStack
    val showPauseDialog = remember { mutableStateOf(true) }
    if (showPauseDialog.value) {
        SnakeDialog(
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.pause),
                    style = MaterialTheme.typography.h3,
                    color = GreenSnake,
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SnakeButtonIcon(imageVector = Icons.Rounded.Home, contentDescription = "home")
                    {
                        showPauseDialog.value = false
                        navController.popBackStack()
                    }
                    MusicButtonIcon(musicViewModel = musicViewModel)
                    SnakeButtonIcon(
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = "resume"
                    )
                    {
                        gameViewModel.onEvent(GameEvent.Pause(false))
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun PauseDialogPreview() {
    PauseDialog(
        navController = rememberNavController(),
        gameViewModel = hiltViewModel(),
        musicViewModel = hiltViewModel()
    )
}