package com.dms.snake.features.game.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dms.snake.features.game.presentation.common.MusicViewModel
import com.dms.snake.features.game.presentation.game.GameScreen
import com.dms.snake.features.game.presentation.highscores.HighscoresScreen
import com.dms.snake.features.game.presentation.menu.MenuScreen
import com.dms.snake.features.game.presentation.util.Screen
import com.dms.snake.ui.theme.SnakeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val musicViewModel: MusicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnakeAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.MenuScreen.route
                    ) {
                        composable(route = Screen.MenuScreen.route) {
                            MenuScreen(navController, musicViewModel)
                        }
                        composable(route = Screen.HighscoresScreen.route) {
                            HighscoresScreen(navController)
                        }
                        composable(route = Screen.GameScreen.route) {
                            GameScreen(navController, musicViewModel)
                        }
                    }
                }
            }
        }

        musicViewModel.init()
    }
}