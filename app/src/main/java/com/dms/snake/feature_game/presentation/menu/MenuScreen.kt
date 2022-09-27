package com.dms.snake.feature_game.presentation.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.R
import com.dms.snake.feature_game.domain.util.Difficulty
import com.dms.snake.feature_game.presentation.common.MusicViewModel
import com.dms.snake.feature_game.presentation.common.components.MusicButtonIcon
import com.dms.snake.feature_game.presentation.common.components.SnakeButton
import com.dms.snake.feature_game.presentation.util.Screen
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun MenuScreen(
    navController: NavController,
    musicViewModel: MusicViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
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
                        text = stringResource(R.string.start),
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
                        text = stringResource(R.string.highscores),
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
                            text = stringResource(R.string.difficulty),
                            style = MaterialTheme.typography.h3
                        )
                        Text(
                            text = stringResource(id = Difficulty.NORMAL.stringRes), // TODO
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
            }
        }

        Row(horizontalArrangement = Arrangement.End) {
            MusicButtonIcon(musicViewModel = musicViewModel)
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(navController = rememberNavController(), musicViewModel = hiltViewModel())
}