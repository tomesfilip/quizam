package com.example.quizam_.presentation

sealed class Screen(val route: String) {
    object GameStartScreen: Screen("game_start_screen")
    object CategoryListScreen: Screen("category_list_screen")
    object CardListScreen: Screen("card_list_screen")
    object GameResultScreen: Screen("game_result_screen")
    object GameLeaderBoardScreen: Screen("game_leader_board_screen")
}