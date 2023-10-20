package technical.test.yprsty.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import technical.test.yprsty.domain.model.Game

interface IGameRepository {
    fun loadGames(): Flow<PagingData<Game>>
    fun searchGames(query: String): Flow<List<Game>>
    fun loadDetailGame(id: Int): Flow<Game>
    fun loadFavoriteGames(): Flow<List<Game>>
    suspend fun insertFavorite(game: Game)
    suspend fun updateFavorite(game: Game)
}