package com.example.sampleapp.feature_game.presentation.game

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.sampleapp.feature_game.domain.model.Food
import com.example.sampleapp.feature_game.domain.util.Orientation
import com.example.sampleapp.feature_game.domain.model.Point
import com.example.sampleapp.feature_game.domain.model.Snake
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    var currentScore by mutableStateOf(0)
        private set

    var snake by mutableStateOf(
        Snake(
            listOf(
                Point(SIZE_SNAKE * 3, 0f),
                Point(SIZE_SNAKE * 2, 0f),
                Point(SIZE_SNAKE, 0f),
                Point(0f, 0f)
            )
        )
    )
        private set

    var food by mutableStateOf<Food?>(null)
        private set

    private var snakeOrientation = Orientation.RIGHT
    private var lastSnakeOrientation = snakeOrientation

    fun launchGame() {
        CoroutineScope(Dispatchers.IO).launch {
            updateFoodPosition()
            while (true) {
                CoroutineScope(Dispatchers.Main).launch {
                    lastSnakeOrientation = snakeOrientation
                    val snakePositions = snake.positionsList
                    val newSnakePositions = arrayListOf<Point>()
                    var snakeHasEat: Boolean
                    // Calculate next position of first point
                    snakePositions.first().let { point ->
                        val firstPoint = when (snakeOrientation) {
                            Orientation.UP -> point.copy(y = point.y - SIZE_SNAKE)
                            Orientation.DOWN -> point.copy(y = point.y + SIZE_SNAKE)
                            Orientation.LEFT -> point.copy(x = point.x - SIZE_SNAKE)
                            Orientation.RIGHT -> point.copy(x = point.x + SIZE_SNAKE)
                        }
                        snakeHasEat = point == food?.position
                        newSnakePositions.add(firstPoint)
                    }
                    snakePositions.forEachIndexed { index, _ ->
                        if (snakeHasEat) {
                            newSnakePositions.add(snakePositions[index].copy())
                        } else if (index != 0) {
                            newSnakePositions.add(snakePositions[index - 1].copy())
                        }
                    }
                    if (snakeHasEat) {
                        increaseCurrentScore()
                        updateFoodPosition()
                    }

                    snake = snake.copy(positionsList = newSnakePositions)
                }
                withContext(Dispatchers.IO) {
                    Thread.sleep(100)
                }
            }
        }
    }

    fun updateOrientation(orientation: Orientation) {
        var allowUpdate = true
        when (lastSnakeOrientation) {
            Orientation.UP -> if (orientation == Orientation.DOWN) allowUpdate = false
            Orientation.DOWN -> if (orientation == Orientation.UP) allowUpdate = false
            Orientation.LEFT -> if (orientation == Orientation.RIGHT) allowUpdate = false
            Orientation.RIGHT -> if (orientation == Orientation.LEFT) allowUpdate = false
        }
        if (allowUpdate) {
            snakeOrientation = orientation
        }
    }

    private fun increaseCurrentScore() {
        currentScore++
    }

    private fun updateFoodPosition() {
        val x = SIZE_SNAKE * (1..20).random()
        val y = SIZE_SNAKE * (1..20).random()
        food = Food(Point(x, y))
    }

    companion object {
        const val SIZE_SNAKE = 35f // TODO estimate this ?
        const val HALF_SIZE_SNAKE = SIZE_SNAKE / 2
    }
}