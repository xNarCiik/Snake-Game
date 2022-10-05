package com.dms.snake.features.game.presentation.game

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.features.game.domain.util.GameState
import com.dms.snake.features.game.presentation.common.MusicViewModel
import com.dms.snake.features.game.presentation.game.components.BottomGame
import com.dms.snake.features.game.presentation.game.components.GameInterface
import com.dms.snake.features.game.presentation.game.components.HeaderGame
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun GameScreen(
    navController: NavController,
    musicViewModel: MusicViewModel,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 22.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderGame(navController, musicViewModel, gameViewModel)

            GameInterface(gameViewModel, Modifier.weight(1f))

            BottomGame(gameViewModel)
        }

        // Use small trick : the dialog will not disappear directly when we do a popBackStack
        val showEndDialog = remember { mutableStateOf(true) }
        if (gameViewModel.gameState == GameState.FINISHED && showEndDialog.value) {
            AlertDialog(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Jeu Fini",
                        style = MaterialTheme.typography.h3,
                        color = GreenSnake,
                        textAlign = TextAlign.Center
                    )
                },
                text = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Vous avez un score final de ${gameViewModel.currentScore}",
                        style = MaterialTheme.typography.h5,
                        color = GreenSnake,
                        textAlign = TextAlign.Center
                    )
                },
                onDismissRequest = {
                    showEndDialog.value = false
                    navController.popBackStack()
                },
                buttons = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                showEndDialog.value = false
                                navController.popBackStack()
                            }
                        ) {
                            Text(
                                text = "Menu",
                                style = MaterialTheme.typography.h5,
                            )
                        }

                        Button(
                            onClick = {
                                gameViewModel.onEvent(GameEvent.Restart)
                            }
                        ) {
                            Text(
                                text = "Rejouer",
                                style = MaterialTheme.typography.h5,
                            )
                        }
                    }

                }
            )
        }
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    val navController = rememberNavController()
    GameScreen(navController = navController, musicViewModel = hiltViewModel())
}