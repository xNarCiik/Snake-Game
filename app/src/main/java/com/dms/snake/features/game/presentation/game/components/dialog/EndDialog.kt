package com.dms.snake.features.game.presentation.game.components.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.R
import com.dms.snake.features.game.presentation.common.components.SnakeDialog
import com.dms.snake.features.game.presentation.common.components.SnakeTextField
import com.dms.snake.features.game.presentation.game.GameEvent
import com.dms.snake.features.game.presentation.game.GameViewModel
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun EndDialog(
    navController: NavController,
    gameViewModel: GameViewModel
) {
    // Use small trick : the dialog will not disappear directly when we do a popBackStack
    val showEndDialog = remember { mutableStateOf(true) }
    var name by rememberSaveable { mutableStateOf("") }

    if (showEndDialog.value) {
        SnakeDialog(
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.title_dialog_end_game),
                    style = MaterialTheme.typography.h3,
                    color = GreenSnake,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        text = String.format(
                            stringResource(R.string.text_dialog_end_game),
                            gameViewModel.currentScore
                        ),
                        style = MaterialTheme.typography.h4,
                        color = GreenSnake,
                        textAlign = TextAlign.Center
                    )
                    // TODO show only if new score
                    SnakeTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        hintValue = "Enter pseudo"
                    )
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            gameViewModel.onEvent(GameEvent.SaveScore(name)) // TODO save only if new score
                            showEndDialog.value = false
                            navController.popBackStack()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.menu),
                            style = MaterialTheme.typography.h4,
                        )
                    }
                    Button(
                        onClick = {
                            gameViewModel.onEvent(GameEvent.SaveScore(name)) // TODO save only if new score
                            gameViewModel.onEvent(GameEvent.Restart)
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.restart),
                            style = MaterialTheme.typography.h4,
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun EndDialogPreview() {
    EndDialog(navController = rememberNavController(), gameViewModel = hiltViewModel())
}