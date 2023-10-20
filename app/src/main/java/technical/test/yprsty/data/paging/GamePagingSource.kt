package technical.test.yprsty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.first
import technical.test.yprsty.data.source.remote.RemoteDataSource
import technical.test.yprsty.data.source.remote.response.GameItem

class GamePagingSource(private val remoteDataSource: RemoteDataSource): PagingSource<Int, GameItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameItem> {
        return try {
            val position = params.key ?: initialPageIndex
            val apiKey = "" // TODO: Filled the api key
            val responseData = remoteDataSource.fetchGames(apiKey, position, params.loadSize).first()
            val listOfGames = responseData.results

            LoadResult.Page(
                data = listOfGames,
                prevKey = if (position == initialPageIndex) null else position - 1,
                nextKey = if (listOfGames.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GameItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val initialPageIndex = 1
    }
}