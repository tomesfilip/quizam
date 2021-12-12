package com.example.quizam_.presentation.card_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizam_.common.Constants
import com.example.quizam_.common.Resource
import com.example.quizam_.domain.use_case.GetQuizCards
import com.example.quizam_.domain.use_case.UserUseCases
import com.example.quizam_.presentation.game_result.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val getQuizCardsUseCase: GetQuizCards,
    private val userUseCases: UserUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _cardListState = mutableStateOf(CardListState())
    val cardListState: State<CardListState> = _cardListState

    private val _cardDetailState = mutableStateOf(CardDetailState())
    val cardDetailState: State<CardDetailState> = _cardDetailState

    private val _currentCardId = mutableStateOf(0)

    private val _userState = mutableStateOf(UserState())
    val userState: State<UserState> = _userState

    private val _gameOverState = mutableStateOf(false)
    val gameOverState: MutableState<Boolean> = _gameOverState

    private var getLastUserJob: Job? = null

    init {
        savedStateHandle.get<String>(Constants.QUERY_CATEGORY_ID)?.let { categoryId ->
            getQuizCards(categoryId.toInt())
        }
        getLastUser()
    }

    private fun getLastUser() {
        getLastUserJob?.cancel()
        getLastUserJob = userUseCases.getLastUser().onEach { user ->
            _userState.value = userState.value.copy(user = user)
        }.launchIn(viewModelScope)
    }

    private fun getQuizCards(id: Int) {
        getQuizCardsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _cardListState.value = CardListState(cards = result.data ?: emptyList())
                    _cardDetailState.value = CardDetailState(quizCard = result.data?.get(_currentCardId.value))
                }
                is Resource.Error -> {
                    _cardListState.value = CardListState(error = result.message ?: "Error occurred")
                }
                is Resource.Loading -> {
                    _cardListState.value = CardListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: QuizCardsEvent) {
        when(event) {
            is QuizCardsEvent.ClickedOption -> {
                viewModelScope.launch {
                    try {
                        if (event.option_answer == _cardDetailState.value.quizCard?.correct_answer) {
                            _userState.value.user?.let {
                                it.userScore += 1
                                userUseCases.updateUser(it)
                            }
                        }
                        if (_currentCardId.value >= 19) {
                            _gameOverState.value = true
                        } else {
                            _currentCardId.value += 1
                            _cardDetailState.value = CardDetailState(
                                cardListState.value.cards[_currentCardId.value]
                            )
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}