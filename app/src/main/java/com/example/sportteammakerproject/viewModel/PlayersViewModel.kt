package com.example.sportteammakerproject.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportteammakerproject.data.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class PlayersViewModel(private val firestoreRepo: FirestoreRepo) : ViewModel() {

    private val _players = MutableStateFlow<Result<List<Player>>>(Result.success(emptyList()))
    val players: StateFlow<Result<List<Player>>> = _players

    init {
        loadPlayers()
    }

    private fun loadPlayers() {
        viewModelScope.launch {
            firestoreRepo.getAllPlayers()
                .catch { exception ->
                    Log.e("PlayersViewModel", "Oyuncuları toplarken istisna: ${exception.message}", exception)
                    _players.value = Result.failure(exception)
                }
                .collect { result ->

                    _players.value = result r
                    if (result.isSuccess) {
                        Log.d("PlayersViewModel", "Oyuncular güncellendi: ${result.getOrNull()?.size ?: 0} oyuncu")
                    } else {
                        Log.e("PlayersViewModel", "Oyuncuları alırken hata: ${result.exceptionOrNull()?.message}")
                    }
                }
        }
    }

}