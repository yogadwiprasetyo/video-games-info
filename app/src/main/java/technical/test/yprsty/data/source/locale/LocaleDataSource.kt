package technical.test.yprsty.data.source.locale

import kotlinx.coroutines.flow.Flow
import technical.test.yprsty.data.source.locale.entity.GameEntity
import technical.test.yprsty.data.source.locale.room.GameDao

class LocaleDataSource(private val dao: GameDao) {
    suspend fun insertFavorite(game: GameEntity) {
        dao.insert(game)
    }

    suspend fun updateGameFavorite(game: GameEntity) {
        dao.update(game)
    }

    fun getFavorites(): Flow<List<GameEntity>> = dao.getFavorites()

    fun getDetail(id: Int): Flow<GameEntity> = dao.getDetail(id)
}