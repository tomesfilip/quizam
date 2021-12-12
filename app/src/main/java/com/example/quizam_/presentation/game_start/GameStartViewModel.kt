package com.example.quizam_.presentation.game_start

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizam_.domain.model.User
import com.example.quizam_.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class GameStartViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _userName = mutableStateOf(
        UserTextFieldState()
    )
    val userName: State<UserTextFieldState> = _userName

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: GameStartEvent) {
        when (event) {
            is GameStartEvent.EnteredUserName -> {
                _userName.value = userName.value.copy(text = event.value)
            }
            is GameStartEvent.InsertUser  -> {
                viewModelScope.launch {
                    try {
                        userUseCases.insertUser(
                            User(
                                userName = userName.value.text,
                                userScore = 0
                            )
                        )
                        _eventFlow.emit(UiEvent.InsertUser)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(msg = e.message ?: "Couldn't save a user")
                        )
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val msg: String) : UiEvent()
        object InsertUser: UiEvent()
    }
}