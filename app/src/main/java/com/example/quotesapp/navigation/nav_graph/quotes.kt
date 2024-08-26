package com.example.quotesapp.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.quotesapp.navigation.Screen
import com.example.quotesapp.navigation.Tab
import com.example.quotesapp.presentation.category_sc.CategoryScreen


fun NavGraphBuilder.quotes(navController: NavController) {
    navigation(
        startDestination = Screen.CategoryScreen.route,
        route = Tab.Quotes.route
    ) {
        composable(route = Screen.CategoryScreen.route) {
            CategoryScreen()
        }
    }
}


