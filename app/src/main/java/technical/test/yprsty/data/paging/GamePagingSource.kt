package technical.test.yprsty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.first
import technical.test.yprsty.data.source.remote.RemoteDataSource
import technical.test.yprsty.domain.model.Game
import technical.test.yprsty.utils.DataMapper
import technical.test.yprsty.utils.apiKey

class GamePagingSource(private val remoteDataSource: RemoteDataSource) : PagingSource<Int, Game>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val position = params.key ?: initialPageIndex
            val responseData = remoteDataSource.fetchGames(
                apiKey,
                position,
                params.loadSize
            ).first()
            val listOfGames = DataMapper.mapGamesResponseToDomain(responseData.results)

            LoadResult.Page(
                data = listOfGames,
                prevKey = if (position == initialPageIndex) null else position - 1,
                nextKey = if (listOfGames.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val initialPageIndex = 1
    }
}