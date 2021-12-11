package com.example.quizam_.presentation.game_start

sealed class GameStartEvent {
    data class EnteredUserName(val value: String) : GameStartEvent()
    object InsertUser: GameStartEvent()
}