package com.dms.snake.feature_game.presentation.game

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
import com.dms.snake.feature_game.presentation.common.MusicViewModel
import com.dms.snake.feature_game.presentation.game.components.BottomGame
import com.dms.snake.feature_game.presentation.game.components.GameInterface
import com.dms.snake.feature_game.presentation.game.components.HeaderGame

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
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    val navController = rememberNavController()
    GameScreen(navController = navController, musicViewModel = hiltViewModel())
}