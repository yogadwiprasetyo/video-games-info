package technical.test.core.data.source.locale

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import technical.test.core.data.source.locale.entity.GameEntity
import technical.test.core.data.source.locale.room.GameDao

class LocaleDataSource(private val dao: GameDao) {
    fun insert(game: GameEntity): Flow<Long> = flow { emit(dao.insert(game)) }

    suspend fun updateGameFavorite(game: GameEntity) = dao.update(game)

    fun getFavorites(): Flow<List<GameEntity>> = dao.getFavorites()

    fun getDetail(id: Int): Flow<GameEntity?> = dao.getDetail(id)
}