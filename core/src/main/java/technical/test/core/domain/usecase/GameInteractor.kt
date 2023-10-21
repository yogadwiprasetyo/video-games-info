package technical.test.core.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import technical.test.core.domain.model.Game
import technical.test.core.domain.repository.IGameRepository

class GameInteractor(private val repository: IGameRepository) : GameUseCase {
    override fun loadGames(): Flow<PagingData<Game>> = repository.loadGames()

    override fun searchGames(query: String): Flow<List<Game>> = repository.searchGames(query)

    override fun loadDetailGame(id: Int): Flow<Game> = repository.loadDetailGame(id)

    override fun loadFavoriteGames(): Flow<List<Game>> = repository.loadFavoriteGames()

    override suspend fun updateFavorite(id: Int) = repository.updateFavorite(id)
}