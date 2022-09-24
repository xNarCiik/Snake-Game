package com.example.sampleapp.feature_game.presentation.menu.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sampleapp.feature_game.domain.util.Difficulty
import com.example.sampleapp.feature_game.presentation.game.GameViewModel
import com.example.sampleapp.feature_game.presentation.util.Screen

@Composable
fun MenuScreen(
    navController: NavController,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Snake",
            style = MaterialTheme.typography.h2
        )
        Button(onClick = { navController.navigate(Screen.GameScreen.route) }) {
            Text(
                text = "Start Game",
                style = MaterialTheme.typography.h6
            )
        }
        Button(onClick = { navController.navigate(Screen.HighscoresScreen.route) }) {
            Text(
                text = "Highscores",
                style = MaterialTheme.typography.h6
            )
        }
        Button(onClick = { gameViewModel.changeDifficulty(Difficulty.HARDCORE) }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Difficulty",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = gameViewModel.difficulty.value.stringValue
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    val navController = rememberNavController()
    MenuScreen(navController)
}