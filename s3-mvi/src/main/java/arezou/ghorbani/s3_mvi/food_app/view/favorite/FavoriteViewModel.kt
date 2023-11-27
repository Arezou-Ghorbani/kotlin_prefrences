package arezou.ghorbani.s3_mvi.food_app.view.favorite

import arezou.ghorbani.s3_mvi.food_app.data.repository.FavoriteRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FavoriteRepository) : ViewModel() {
    val favoriteIntent = Channel<FavoriteIntent>()
    private val _state = MutableStateFlow<FavoriteState>(FavoriteState.Empty)
    val state: StateFlow<FavoriteState> get() = _state

    init {
        handleIntents()
    }

    private fun handleIntents() = viewModelScope.launch {
        favoriteIntent.consumeAsFlow().collect { intent ->
            when (intent) {
                is FavoriteIntent.LoadFavorites -> fetchingLoadFavorites()
            }
        }
    }

    private fun fetchingLoadFavorites() = viewModelScope.launch {
        repository.foodsList().collect {
            _state.value = if (it.isEmpty()) {
                FavoriteState.Empty
            } else {
                FavoriteState.LoadFavorites(it)
            }
        }
    }
}