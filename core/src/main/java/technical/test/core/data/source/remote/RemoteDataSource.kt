package technical.test.core.data.source.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import technical.test.core.data.source.remote.network.ApiService
import technical.test.core.data.source.remote.response.ResponseGame
import technical.test.core.data.source.remote.response.ResponseGames

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun fetchGames(apiKey: String, page: Int, pageSize: Int): Flow<ResponseGames> = flow {
        emit(apiService.fetchGames(apiKey, page, pageSize))
    }

    suspend fun searchGames(apiKey: String, query: String): Flow<ResponseGames> = flow {
        emit(apiService.searchGames(apiKey, query))
    }

    suspend fun fetchDetailGame(apiKey: String, id: Int): Flow<ResponseGame> = flow {
        emit(apiService.fetchDetailGame(id, apiKey))
    }
}