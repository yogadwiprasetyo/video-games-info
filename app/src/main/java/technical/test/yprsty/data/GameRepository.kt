package technical.test.yprsty.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import technical.test.yprsty.data.paging.GamePagingSource
import technical.test.yprsty.data.source.locale.LocaleDataSource
import technical.test.yprsty.data.source.remote.RemoteDataSource
import technical.test.yprsty.domain.model.Game
import technical.test.yprsty.domain.repository.IGameRepository
import technical.test.yprsty.utils.DataMapper
import technical.test.yprsty.utils.apiKey

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localeDataSource: LocaleDataSource,
    private val gamePagingSource: GamePagingSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IGameRepository {
    override fun loadGames(): Flow<PagingData<Game>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { gamePagingSource }
    ).flow

    override fun searchGames(query: String): Flow<List<Game>> = flow {
        val response = remoteDataSource.searchGames(apiKey, query).first()
        val result = DataMapper.mapGamesResponseToDomain(response.results)
        emit(result)
    }.flowOn(defaultDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun loadDetailGame(id: Int): Flow<Game> {
        return localeDataSource.getDetail(id).flatMapConcat {
            if (it == null) {
                return@flatMapConcat remoteDataSource.fetchDetailGame(apiKey, id)
                    .map { response -> DataMapper.mapGameResponseToEntity(response) }
                    .flatMapConcat { entity -> localeDataSource.insert(entity) }
                    .flatMapConcat { localeDataSource.getDetail(id) }
                    .filterNotNull()
                    .map { entity -> DataMapper.mapGameEntityToDomain(entity) }
            } else {
                return@flatMapConcat flowOf(DataMapper.mapGameEntityToDomain(it))
            }
        }.flowOn(defaultDispatcher)
    }

    override suspend fun updateFavorite(id: Int) {
        val entity = localeDataSource.getDetail(id).firstOrNull()
            ?: throw NullPointerException("User is not available")
        entity.isFavorite = !entity.isFavorite
        localeDataSource.updateGameFavorite(entity)
    }

    override fun loadFavoriteGames(): Flow<List<Game>> = flow {
        val favoriteGamesEntity = localeDataSource.getFavorites().first()
        val favoriteGames = DataMapper.mapGamesEntityToDomain(favoriteGamesEntity)
        emit(favoriteGames)
    }.flowOn(defaultDispatcher)
}