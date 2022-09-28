package com.dms.snake.features.game.presentation.game

import com.dms.snake.features.game.domain.model.Point

data class SnakeState(
    val positionsList: List<Point> = initializePositionsList()
) {
    companion object {
        const val SIZE_SNAKE = 30f // TODO estimate this ?
        const val HALF_SIZE_SNAKE = SIZE_SNAKE / 2
        private const val LENGTH_SNAKE = 5
        private fun initializePositionsList(): List<Point> {
            val positionsList = ArrayList<Point>()
            for (i in 0..LENGTH_SNAKE) {
                positionsList.add(Point(SIZE_SNAKE * i, 0f))
            }
            return positionsList.reversed()
        }
    }
}
