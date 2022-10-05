package com.dms.snake.features.game.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dms.snake.features.game.domain.model.Score

@Database(
    entities = [Score::class],
    version = 1
)
abstract class SnakeDatabase : RoomDatabase() {

    abstract val scoreDao: ScoreDao

    companion object {
        const val DATABASE_NAME = "snake_db"
    }
}