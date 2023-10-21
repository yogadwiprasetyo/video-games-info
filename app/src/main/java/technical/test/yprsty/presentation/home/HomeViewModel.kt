package technical.test.yprsty.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import technical.test.core.domain.usecase.GameUseCase
import timber.log.Timber

class HomeViewModel(private val useCase: GameUseCase) : ViewModel() {
    private val _games = MutableLiveData<GamesUiState>(GamesUiState.Loading)
    val games: LiveData<GamesUiState> = _games

    fun loadGames() {
        viewModelScope.launch {
            useCase.loadGames().cachedIn(viewModelScope)
                .catch { e ->
                    Timber.e(e)
                    e.printStackTrace()
                    _games.postValue(GamesUiState.Error(e))
                }
                .collect {
                    Timber.i(it.toString())
                    _games.postValue(GamesUiState.Success(it))
                }
        }
    }
}

sealed class GamesUiState {
    data class Success(val games: PagingData<technical.test.core.domain.model.Game>) : GamesUiState()
    data class Error(val exception: Throwable) : GamesUiState()
    data object Loading : GamesUiState()
}