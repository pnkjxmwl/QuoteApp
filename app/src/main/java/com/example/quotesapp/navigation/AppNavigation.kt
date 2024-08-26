package com.example.quotesapp.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quotesapp.navigation.nav_graph.quotes
import com.example.quotesapp.navigation.nav_graph.saved


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(){

    val navController= rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Log.d("msg",currentRoute.toString())
    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.CategoryScreen.route -> {
                    TopAppBar(title = {
                        Text(
                            text = "Categories",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                        )
                    })
                }
                Screen.SavedQuoteScreen.route -> {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Favourite Quotes",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1
                            )
                        }
                    )
                }

            }
        },
        bottomBar = {
                myBottomNavigationBar(navController = navController)
        }
    ) {
         contentPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            navController = navController,
            startDestination = Tab.Quotes.route ) {
            quotes(navController)
            saved(navController)
        }
    }
}

@Composable
private  fun myBottomNavigationBar(navController: NavController){

    NavigationBar(
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination= navBackStackEntry?.destination

        bottomNavBarTabs.forEach { tab->
            val selected= currentDestination?.hierarchy?.any { it.route==tab.route }==true
            NavigationBarItem(selected = selected,
                onClick = {
                    navController.navigate(tab.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState=true
                        }
                        launchSingleTop=true
                        restoreState=true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) tab.selectedIcon else tab.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = tab.label,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                        )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.Gray,

                )

            )

        }

    }


}