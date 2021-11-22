package com.example.quizam_.presentation

sealed class Screen(val route: String) {
    object CategoryListScreen: Screen("category_list_screen")
    object CardListScreen: Screen("card_list_screen")
}