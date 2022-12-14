package com.dms.snake.features.game.presentation.menu

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.R
import com.dms.snake.features.game.domain.util.Difficulty
import com.dms.snake.features.game.presentation.common.MusicViewModel
import com.dms.snake.features.game.presentation.common.components.MusicButtonIcon
import com.dms.snake.features.game.presentation.common.components.SnakeButton
import com.dms.snake.features.game.presentation.util.Screen
import com.dms.snake.ui.theme.GreenSnake

private const val privacyUrl = "https://snake-dms.000webhostapp.com/privacy.htm"

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
                verticalArrangement = Arrangement.spacedBy(16.dp)
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
                        text = stringResource(R.string.scores),
                        style = MaterialTheme.typography.h3
                    )
                }

                val context = LocalContext.current
                SnakeButton(
                    modifier = modifierMaxWidth,
                    onClick = {
                        // TODO with preferences
                        Toast.makeText(context, "In progress..", Toast.LENGTH_SHORT).show()
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

                SnakeButton(
                    modifier = modifierMaxWidth,
                    onClick = {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse(privacyUrl))
                        )
                    }
                ) {
                    Text(
                        text = stringResource(R.string.privacy),
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
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

