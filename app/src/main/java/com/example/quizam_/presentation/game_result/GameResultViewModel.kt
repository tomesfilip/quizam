package com.example.quizam_.presentation.game_result

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizam_.domain.model.User
import com.example.quizam_.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameResultViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _userState = mutableStateOf(UserState())
    val userState: State<UserState> = _userState

    private val _userName = mutableStateOf("")
    val userName: MutableState<String> = _userName

    private val _userScore = mutableStateOf(0)
    val userScore: MutableState<Int> = _userScore

    private var getLastUserJob: Job? = null

    init {
        Log.v("GameResultViewModel", "initializing...")
        getLastUser()
    }

    private fun getLastUser() {
        Log.v("GameResultViewModel", "private function getLastUser() start..")
        getLastUserJob?.cancel()
        getLastUserJob = userUseCases.getLastUser().onEach { user ->
            _userState.value = userState.value.copy(user = user)
        }.launchIn(viewModelScope)
//        userUseCases.getLastUser().also { user ->
//            if (user != null) {
//                _userName.value = user.userName
//                _userScore.value = user.userScore
//            }
//        }
    }
}