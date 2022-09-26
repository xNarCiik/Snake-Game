package com.dms.snake.feature_game.presentation.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.feature_game.domain.util.Difficulty
import com.dms.snake.feature_game.presentation.menu.components.SnakeButton
import com.dms.snake.feature_game.presentation.util.Screen
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun MenuScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Snake",
            style = MaterialTheme.typography.h1,
            color = GreenSnake
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val modifierMaxWidth = Modifier.fillMaxWidth()
            Column(
                modifier = modifierMaxWidth,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                SnakeButton(
                    modifier = modifierMaxWidth,
                    onClick = {
                        navController.navigate(Screen.GameScreen.route)
                    }
                ) {
                    Text(
                        text = "Start",
                        style = MaterialTheme.typography.h3
                    )
                }

                SnakeButton(
                    modifier = modifierMaxWidth,
                    onClick = {
                        navController.navigate(Screen.HighscoresScreen.route)
                    }
                ) {
                    Text(
                        text = "Highscores",
                        style = MaterialTheme.typography.h3
                    )
                }

                SnakeButton(
                    modifier = modifierMaxWidth,
                    onClick = {
                        // TODO with preferences
                    }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Difficulty",
                            style = MaterialTheme.typography.h3
                        )
                        Text(
                            text = Difficulty.NORMAL.stringValue, // TODO
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
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