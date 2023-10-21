package technical.test.yprsty.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import technical.test.yprsty.domain.model.Game
import technical.test.yprsty.domain.usecase.GameUseCase
import timber.log.Timber

class SearchViewModel(private val useCase: GameUseCase) : ViewModel() {
    private val _resultGames = MutableLiveData<SearchGamesUiState>(SearchGamesUiState.Empty)
    val results: LiveData<SearchGamesUiState> = _resultGames

    fun searchGames(query: String) {
        if (query.isEmpty()) {
            _resultGames.postValue(SearchGamesUiState.Empty)
            return
        }

        viewModelScope.launch {
            _resultGames.postValue(SearchGamesUiState.Loading)
            useCase.searchGames(query)
                .catch { e ->
                    Timber.e(e)
                    e.printStackTrace()
                    _resultGames.postValue(SearchGamesUiState.Error(e))
                }
                .collect {
                    Timber.i(it.toString())
                    if (it.isEmpty()) {
                        _resultGames.postValue(SearchGamesUiState.Empty)
                    } else {
                        _resultGames.postValue(SearchGamesUiState.Success(it))
                    }
                }
        }
    }
}

sealed class SearchGamesUiState {
    data class Success(val results: List<Game>) : SearchGamesUiState()
    data class Error(val throwable: Throwable) : SearchGamesUiState()
    data object Loading : SearchGamesUiState()
    data object Empty : SearchGamesUiState()
}