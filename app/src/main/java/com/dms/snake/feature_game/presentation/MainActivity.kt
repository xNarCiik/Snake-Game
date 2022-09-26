package com.dms.snake.feature_game.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dms.snake.BackgroundMusicService
import com.dms.snake.feature_game.presentation.game.GameScreen
import com.dms.snake.feature_game.presentation.highscores.HighscoresScreen
import com.dms.snake.feature_game.presentation.menu.MenuScreen
import com.dms.snake.feature_game.presentation.util.Screen
import com.dms.snake.ui.theme.SnakeAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                            MenuScreen(navController)
                        }
                        composable(route = Screen.HighscoresScreen.route) {
                            HighscoresScreen(navController)
                        }
                        composable(route = Screen.GameScreen.route) {
                            GameScreen(navController)
                        }
                    }
                }
            }
        }

        startService(Intent(this, BackgroundMusicService::class.java).apply {
            action = BackgroundMusicService.PLAY_MUSIC_ACTION
        })
    }
}