package com.dms.snake.features.game.presentation.game.components.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.R
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
    if (showEndDialog.value) {
        AlertDialog(
            modifier = Modifier
                .border(BorderStroke(2.dp, GreenSnake)),
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
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = String.format(
                        stringResource(R.string.text_dialog_end_game),
                        gameViewModel.currentScore
                    ),
                    style = MaterialTheme.typography.h5,
                    color = GreenSnake,
                    textAlign = TextAlign.Center
                )
            },
            onDismissRequest = { },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            showEndDialog.value = false
                            navController.popBackStack()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.menu),
                            style = MaterialTheme.typography.h5,
                        )
                    }

                    Button(
                        onClick = {
                            gameViewModel.onEvent(GameEvent.Restart)
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.restart),
                            style = MaterialTheme.typography.h5,
                        )
                    }
                }
            },
            containerColor = Color.Black
        )
    }
}

@Preview
@Composable
fun EndDialogPreview() {
    EndDialog(navController = rememberNavController(), gameViewModel = hiltViewModel())
}