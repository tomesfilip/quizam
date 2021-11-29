package com.example.quizam_.presentation.game_start

import android.util.Log
import android.util.Log.DEBUG
import android.util.Log.INFO
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizam_.BuildConfig.DEBUG
import com.example.quizam_.data.repository.UserRepositoryImpl
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
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: GameStartEvent) {
        when (event) {
            is GameStartEvent.EnteredUserName -> {
                _userName.value = userName.value
            }
            is GameStartEvent.SaveUser -> {
                viewModelScope.launch {
                    try {
                        userUseCases.insertUser(
                            User(
                                userName = userName.value,
                                userScore = 0
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveUser)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        object SaveUser: UiEvent()
    }
}