package com.example.note.navigation

sealed class NavigationScreen(val route: String) {
    object Home : NavigationScreen("home")
    object Item : NavigationScreen("item/{itemId}")
    object Setting : NavigationScreen("setting")

    fun createRoute(itemId: Int): String {
        return when (this) {
            Home -> Home.route
            Item -> "item/$itemId"
            Setting -> Setting.route
        }
    }
}
