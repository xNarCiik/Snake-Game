package com.example.sampleapp.feature_game.presentation.menu.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sampleapp.feature_game.domain.util.Difficulty
import com.example.sampleapp.feature_game.presentation.util.Screen

@Composable
fun MenuScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Snake",
            style = MaterialTheme.typography.h2
        )
        // TODO custom button
        OutlinedButton(
            onClick = {
                navController.navigate(Screen.GameScreen.route)
            },
            border = BorderStroke(1.dp, Color.Red),
            shape = RoundedCornerShape(50), // = 50% percent
            // or shape = CircleShape
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
        ) {
            Text(
                text = "Start Game",
                style = MaterialTheme.typography.h6
            )
        }

        OutlinedButton(
            onClick = {
                navController.navigate(Screen.HighscoresScreen.route)
            },
            border = BorderStroke(1.dp, Color.Red),
            shape = RoundedCornerShape(50), // = 50% percent
            // or shape = CircleShape
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
        ) {
            Text(
                text = "Highscores",
                style = MaterialTheme.typography.h6
            )
        }

        OutlinedButton(
            onClick = {
                // TODO with preferences
            },
            border = BorderStroke(1.dp, Color.Red),
            shape = RoundedCornerShape(50), // = 50% percent
            // or shape = CircleShape
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Difficulty",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = Difficulty.NORMAL.stringValue // TODO
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