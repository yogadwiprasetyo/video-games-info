package technical.test.yprsty.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localeDataSource: LocaleDataSource,
    private val gamePagingSource: GamePagingSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
): IGameRepository {
    override fun loadGames(): Flow<PagingData<Game>> = flow {
        val pagingData = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { gamePagingSource }
        ).flow.first().map { DataMapper.mapGameItemResponseToDomain(it) }
        emit(pagingData)
    }.flowOn(defaultDispatcher)

    override fun searchGames(query: String): Flow<List<Game>> = flow {
        val apiKey = "" // TODO: Put with the real api key
        val response = remoteDataSource.searchGames(apiKey, query).first()
        val result = DataMapper.mapGamesResponseToDomain(response.results)
        emit(result)
    }.flowOn(defaultDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun loadDetailGame(id: Int): Flow<Game> {
        val apiKey = "" // TODO: Put with the real api key
        return localeDataSource.getDetail(id).flatMapConcat {
            if (it == null) {
                return@flatMapConcat remoteDataSource.fetchDetailGame(apiKey, id)
                    .map { response -> DataMapper.mapGameResponseToDomain(response) }
            } else {
                return@flatMapConcat flowOf(DataMapper.mapGameEntityToDomain(it))
            }
        }.flowOn(defaultDispatcher)
    }

    override suspend fun insertFavorite(game: Game) {
        val gameEntity = DataMapper.mapDomainToGameEntity(game)
        localeDataSource.insertFavorite(gameEntity)
    }

    override suspend fun updateFavorite(game: Game) {
        val updateEntity = DataMapper.mapDomainToGameEntity(game)
        localeDataSource.updateGameFavorite(updateEntity)
    }

    override fun loadFavoriteGames(): Flow<List<Game>> = flow {
        val favoriteGamesEntity = localeDataSource.getFavorites().first()
        val favoriteGames = DataMapper.mapGamesEntityToDomain(favoriteGamesEntity)
        emit(favoriteGames)
    }.flowOn(defaultDispatcher)
}