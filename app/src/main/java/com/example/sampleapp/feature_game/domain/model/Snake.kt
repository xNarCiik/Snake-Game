package com.example.sampleapp.feature_game.domain.model

data class Snake(
    val positionsList: List<Point>
){
    companion object {
        const val SIZE_SNAKE = 30f // TODO estimate this ?
        const val HALF_SIZE_SNAKE = SIZE_SNAKE / 2
    }
}
