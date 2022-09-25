package com.example.sampleapp.feature_game.presentation.game

import androidx.compose.runtime.*
import androidx.compose.ui.unit.IntSize
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
                Point(Snake.SIZE_SNAKE * 3, 0f),
                Point(Snake.SIZE_SNAKE * 2, 0f),
                Point(Snake.SIZE_SNAKE, 0f),
                Point(0f, 0f)
            )
        )
    )
        private set

    var food by mutableStateOf<Food?>(null)
        private set

    private var snakeOrientation = Orientation.RIGHT
    private var lastSnakeOrientation = snakeOrientation

    private var screenSize: IntSize? = null
    var gameIsLaunched = false // TODO remove this check ?

    fun launchGame(screenSize: IntSize) {
        if (gameIsLaunched) return
        gameIsLaunched = true
        this.screenSize = screenSize
        CoroutineScope(Dispatchers.IO).launch {
            updateFoodPosition()
            var loose = false
            while (!loose) {
                CoroutineScope(Dispatchers.Main).launch {
                    lastSnakeOrientation = snakeOrientation
                    val snakePositions = snake.positionsList
                    val newSnakePositions = arrayListOf<Point>()
                    var snakeHasEat: Boolean
                    // Calculate next position of first point
                    snakePositions.first().let { point ->
                        val firstPoint = when (snakeOrientation) {
                            Orientation.UP -> point.copy(y = point.y - Snake.SIZE_SNAKE)
                            Orientation.DOWN -> point.copy(y = point.y + Snake.SIZE_SNAKE)
                            Orientation.LEFT -> point.copy(x = point.x - Snake.SIZE_SNAKE)
                            Orientation.RIGHT -> point.copy(x = point.x + Snake.SIZE_SNAKE)
                        }

                        // Check if snake eat itself or wall
                        loose =
                            snakePositions.contains(firstPoint) || firstPoint.x < 0 || firstPoint.y < 0 || firstPoint.y >= screenSize.height || firstPoint.x >= screenSize.width
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
        screenSize?.let {
            val x = Snake.SIZE_SNAKE * (1 until (it.width/Snake.SIZE_SNAKE).toInt()).random()
            val y = Snake.SIZE_SNAKE * (1 until (it.height/Snake.SIZE_SNAKE).toInt()).random()
            food = Food(Point(x, y))
        }
    }
}