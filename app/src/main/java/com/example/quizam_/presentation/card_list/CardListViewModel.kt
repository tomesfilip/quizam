package com.example.quizam_.presentation.card_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizam_.common.Constants
import com.example.quizam_.common.Resource
import com.example.quizam_.domain.model.QuizCard
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

    private val _currentCard = mutableStateOf(CardDetailState())
    val currentCard: State<CardDetailState> = _currentCard

    private val _currentCardId = mutableStateOf(0)
    val currentCardId: State<Int> = _currentCardId

    init {
        savedStateHandle.get<String>(Constants.QUERY_CATEGORY_ID)?.let { categoryId ->
            getQuizCards(categoryId.toInt())
        }
    }

    fun nextQuizCard() {
        if (_state.value.cards.isNotEmpty()) {
            _currentCardId.value += 1
            if (_currentCardId.value < _state.value.cards.size) {
                _currentCard.value = CardDetailState(quizCard = _state.value.cards[_currentCardId.value])
            } else {
                _currentCard.value = CardDetailState(
                    quizCard = _state.value.cards[_state.value.cards.size - 1],
                    error = "End of questions"
                )
            }
        }
    }

    private fun getQuizCard(id: Int): QuizCard? {
        return _state.value.cards[id]
    }

    private fun getQuizCards(id: Int) {
        getQuizCardsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CardListState(cards = result.data ?: emptyList())
                    _currentCard.value = CardDetailState(quizCard = result.data?.get(_currentCardId.value) ?: null)
                }
                is Resource.Error -> {
                    _state.value = CardListState(error = result.message ?: "Error occurred")
                    _currentCard.value = CardDetailState(error = result.message ?: "Error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CardListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: QuizCardsEvent) {
        when(event) {

        }
    }
}