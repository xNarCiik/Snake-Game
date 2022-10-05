package com.dms.snake.features.game.presentation.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.dms.snake.features.game.presentation.game.components.dialog.EndDialog
import com.dms.snake.features.game.presentation.game.components.dialog.PauseDialog

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

            HeaderGame(gameViewModel)

            GameInterface(gameViewModel, Modifier.weight(1f))

            BottomGame(gameViewModel)
        }

        when (gameViewModel.gameState) {
            GameState.FINISHED -> EndDialog(
                navController = navController,
                gameViewModel = gameViewModel
            )
            GameState.PAUSED -> PauseDialog(
                navController = navController,
                gameViewModel = gameViewModel,
                musicViewModel = musicViewModel
            )
            else -> {}
        }
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    val navController = rememberNavController()
    GameScreen(navController = navController, musicViewModel = hiltViewModel())
}