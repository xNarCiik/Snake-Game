package com.dms.snake.features.game.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score(
    val name: String,
    val score: Int,
    @PrimaryKey val id: Int? = null
)