package com.example.quotesapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route:String){

    data object CategoryScreen : Screen("category_screen");
    data object SavedQuoteScreen: Screen("savedquote_screen")

}

sealed class Tab(
    val route: String,
    val icon:ImageVector,
    val selectedIcon:ImageVector,
    val label:String
){
    data object Quotes:Tab(
        route = "quotes_tab",
        icon= Icons.Outlined.Home,
        label = "Quotes",
        selectedIcon =Icons.Filled.Home
    )
    data object Saved:Tab(
        route = "saved_tab",
        icon=Icons.Default.FavoriteBorder,
        label = "Saved",
        selectedIcon =Icons.Filled.Favorite
    )
}

val bottomNavBarTabs= listOf(
    Tab.Quotes,
    Tab.Saved
)





















