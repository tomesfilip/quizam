package com.example.quizam_.presentation.game_start

import androidx.lifecycle.ViewModel
import com.example.quizam_.data.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GameStartViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {

}