package com.example.quizam_.presentation.card_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizam_.common.Resource
import com.example.quizam_.domain.use_case.GetCards
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val getCardsUseCase: GetCards,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CardListState())
    val state: State<CardListState> = _state

    init {
        savedStateHandle.get<String>("12")?.let { id -> getCards(id.toInt()) }
    }

    private fun getCards(id: Int) {
        getCardsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CardListState(cards = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CardListState(error = result.message ?: "Error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CardListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}