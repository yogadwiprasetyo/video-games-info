package technical.test.yprsty.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import technical.test.core.domain.model.Game
import technical.test.core.domain.usecase.GameUseCase
import timber.log.Timber

class DetailViewModel(private val useCase: GameUseCase) : ViewModel() {
    private val _game = MutableLiveData<GameUiState>(GameUiState.Loading)
    val game: LiveData<GameUiState> = _game

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        Timber.e(e)
        e.printStackTrace()
        _game.postValue(GameUiState.Error(e))
    }

    fun loadDetail(id: Int) {
        viewModelScope.launch {
            useCase.loadDetailGame(id)
                .catch { e ->
                    Timber.e(e)
                    e.printStackTrace()
                    _game.postValue(GameUiState.Error(e))
                }
                .collect {
                    Timber.i(it.toString())
                    _game.postValue(GameUiState.Success(it))
                }
        }
    }

    fun updateFavoriteState(id: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            useCase.updateFavorite(id)
        }
    }
}

sealed class GameUiState {
    data class Success(val game: Game) : GameUiState()
    data class Error(val throwable: Throwable) : GameUiState()
    data object Loading : GameUiState()
}