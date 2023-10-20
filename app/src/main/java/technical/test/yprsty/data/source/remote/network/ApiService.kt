package technical.test.yprsty.data.source.remote.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import technical.test.yprsty.data.source.remote.response.ResponseGame
import technical.test.yprsty.data.source.remote.response.ResponseGames

interface ApiService {

    @GET("games")
    suspend fun fetchGames(
        @Query("key") apiKey: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): ResponseGames

    @GET("games")
    suspend fun searchGames(
        @Query("key") apiKey: String,
        @Query("search") query: String,
        @Query("search_precise") preciseResult: Boolean = true
    ): ResponseGames

    @GET("games/{id}")
    suspend fun fetchDetailGame(
        @Path("id") id: Int,
        @Query("key") apiKey: String,
    ): ResponseGame

}