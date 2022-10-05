package com.dms.snake.features.game.presentation.scores

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.dms.snake.features.game.presentation.common.components.SnakeButton
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun ScoresScreen(
    navController: NavController,
    scoresViewModel: ScoresViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.scores),
            style = MaterialTheme.typography.h1,
            color = GreenSnake
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 22.dp)
        ) {
            itemsIndexed(
                items = scoresViewModel.scores
            ) { index, score ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 22.dp),
                        text = "$index. ${score.name} : ${score.score}",
                        style = MaterialTheme.typography.h3,
                        color = GreenSnake
                    )

                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp)
                        .background(GreenSnake))
                }
            }
        }

        SnakeButton(onClick = { navController.popBackStack() }) {
            Icon(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(35.dp),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back",
                tint = GreenSnake
            )
            Text(
                text = stringResource(R.string.back),
                style = MaterialTheme.typography.h3,
                color = GreenSnake
            )
        }
    }
}

@Preview
@Composable
fun ScoresScreenPreview() {
    val navController = rememberNavController()
    ScoresScreen(navController = navController)
}