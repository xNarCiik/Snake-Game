package com.dms.snake.features.game.data.data_source

import androidx.room.*
import com.dms.snake.features.game.domain.model.Score
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Query("SELECT * FROM score")
    fun getScores(): Flow<List<Score>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: Score)
}