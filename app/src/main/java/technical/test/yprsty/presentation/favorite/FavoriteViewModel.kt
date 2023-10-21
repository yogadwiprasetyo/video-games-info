package technical.test.yprsty.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import technical.test.yprsty.domain.model.Game
import technical.test.yprsty.domain.usecase.GameUseCase
import timber.log.Timber

class FavoriteViewModel(private val useCase: GameUseCase) : ViewModel() {
    private val _gameFavorites = MutableLiveData<FavoriteGamesUiState>()
    val gameFavorites: LiveData<FavoriteGamesUiState> = _gameFavorites

    fun loadFavorites() {
        viewModelScope.launch {
            useCase.loadFavoriteGames()
                .catch {e ->
                    Timber.e(e)
                    e.printStackTrace()
                    _gameFavorites.postValue(FavoriteGamesUiState.Error(e))
                }
                .collect {
                    Timber.i(it.toString())
                    if (it.isEmpty()) {
                        _gameFavorites.postValue(FavoriteGamesUiState.Empty)
                    } else {
                        _gameFavorites.postValue(FavoriteGamesUiState.Success(it))
                    }
                }
        }
    }
}

sealed class FavoriteGamesUiState {
    data class Success(val gameFavorites: List<Game>) : FavoriteGamesUiState()
    data class Error(val throwable: Throwable) : FavoriteGamesUiState()
    data object Empty : FavoriteGamesUiState()
}