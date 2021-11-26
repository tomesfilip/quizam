package com.example.quizam_.presentation.card_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizam_.common.Resource
import com.example.quizam_.domain.use_case.GetQuizCards
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val getQuizCardsUseCase: GetQuizCards,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CardListState())
    val state: State<CardListState> = _state

    init {
        savedStateHandle.get<String>("12")?.let { id -> getQuizCardsUseCase(id.toInt()) }
    }

    private fun getQuizCards(id: Int) {
        getQuizCardsUseCase(id).onEach { result ->
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