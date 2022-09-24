package com.example.sampleapp.feature_game.presentation.game.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sampleapp.feature_game.presentation.game.GameViewModel

@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = gameViewModel.difficulty.value.stringValue,
            style = MaterialTheme.typography.h6
        )
    }
}