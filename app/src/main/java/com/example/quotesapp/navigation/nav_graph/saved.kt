package com.example.quotesapp.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.quotesapp.navigation.Screen
import com.example.quotesapp.navigation.Tab
import com.example.quotesapp.presentation.savedquote_sc.SavedQuoteScreen


fun NavGraphBuilder.saved(navController: NavController){
    navigation(
        startDestination = Screen.SavedQuoteScreen.route,
        route = Tab.Saved.route
    ){
        composable(route = Screen.SavedQuoteScreen.route){
            SavedQuoteScreen()
        }
    }
}