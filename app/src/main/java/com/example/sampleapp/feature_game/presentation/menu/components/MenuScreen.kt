package com.example.sampleapp.feature_game.presentation.menu.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sampleapp.feature_game.domain.util.Difficulty
import com.example.sampleapp.feature_game.presentation.util.Screen
import com.example.sampleapp.ui.theme.GreenSnake

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
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // TODO custom button
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.GameScreen.route)
                    },
                    border = BorderStroke(1.dp, GreenSnake),
                    shape = RoundedCornerShape(50), // = 50% percent
                    // or shape = CircleShape
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = GreenSnake)
                ) {
                    Text(
                        text = "Start",
                        style = MaterialTheme.typography.h3
                    )
                }

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.HighscoresScreen.route)
                    },
                    border = BorderStroke(1.dp, GreenSnake),
                    shape = RoundedCornerShape(50), // = 50% percent
                    // or shape = CircleShape
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = GreenSnake)
                ) {
                    Text(
                        text = "Highscores",
                        style = MaterialTheme.typography.h3
                    )
                }

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        // TODO with preferences
                    },
                    border = BorderStroke(1.dp, GreenSnake),
                    shape = RoundedCornerShape(50), // = 50% percent
                    // or shape = CircleShape
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = GreenSnake)
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