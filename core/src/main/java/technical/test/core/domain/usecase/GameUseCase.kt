package technical.test.core.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import technical.test.core.domain.model.Game

interface GameUseCase {
    fun loadGames(): Flow<PagingData<Game>>
    fun searchGames(query: String): Flow<List<Game>>
    fun loadDetailGame(id: Int): Flow<Game>
    fun loadFavoriteGames(): Flow<List<Game>>
    suspend fun updateFavorite(id: Int)
}