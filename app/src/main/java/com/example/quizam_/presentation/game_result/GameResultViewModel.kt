package com.example.quizam_.presentation.game_result

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizam_.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameResultViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _usersState = mutableStateOf(UsersState())
    val usersState: State<UsersState> = _usersState

    private var getUsersJob: Job? = null

    init {
        Log.v("GameResultViewModel", "initializing...")
        getUsers()
    }

    private fun getUsers() {
        getUsersJob?.cancel()
        getUsersJob = userUseCases.getUsers().onEach { users ->
            Log.v("GameResultViewModel", "getting users")
            _usersState.value = usersState.value.copy(
                users = users
            )
        }
            .launchIn(viewModelScope)
    }
}