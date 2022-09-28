package com.dms.snake.features.game.presentation.highscores

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.features.game.presentation.common.components.SnakeButton
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun HighscoresScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Highscores",
            style = MaterialTheme.typography.h1,
            color = GreenSnake
        )

        Column(modifier = Modifier.weight(1f)) {

        }

        SnakeButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back",
                tint = GreenSnake
            )
            Text(
                text = "Back",
                style = MaterialTheme.typography.h3,
                color = GreenSnake
            )
        }
    }
}

@Preview
@Composable
fun HighscoresScreenPreview() {
    val navController = rememberNavController()
    HighscoresScreen(navController = navController)
}