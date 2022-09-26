package com.dms.snake.feature_game.presentation.game

import com.dms.snake.feature_game.domain.model.Point

data class SnakeState(
    val positionsList: List<Point> = defaultPositionsList
) {
    companion object {
        const val SIZE_SNAKE = 30f // TODO estimate this ?
        const val HALF_SIZE_SNAKE = SIZE_SNAKE / 2
        private val defaultPositionsList = listOf(
            Point(SIZE_SNAKE * 3, 0f),
            Point(SIZE_SNAKE * 2, 0f),
            Point(SIZE_SNAKE, 0f),
            Point(0f, 0f)
        )
    }
}
