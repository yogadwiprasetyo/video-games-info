package technical.test.yprsty.data.source.locale.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import technical.test.yprsty.data.source.locale.entity.GameEntity

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: GameEntity)

    @Update
    suspend fun update(game: GameEntity)

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE id = :id")
    fun getDetail(id: Int): Flow<GameEntity?>
}