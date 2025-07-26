package com.example.note.navigation

sealed class NavigationScreen(val route: String) {
    object Home : NavigationScreen("home")
    object Item : NavigationScreen("item")
}