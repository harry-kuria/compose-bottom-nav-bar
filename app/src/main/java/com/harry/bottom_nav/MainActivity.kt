package com.harry.bottom_nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.harry.bottom_nav.ui.theme.BottomnavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomnavTheme {
               MainScreen()

            }
        }
    }
    @Composable
    fun MainScreen() {

        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->

            val graph =
                navController.createGraph(startDestination = Screen.Home.route) {
                    composable(route = Screen.Cart.route) {
                        CartScreen()
                    }
                    composable(route = Screen.Setting.route) {
                        SettingScreen()
                    }
                    composable(route = Screen.Home.route) {
                        HomeScreen()
                    }
                    composable(route = Screen.Profile.route) {
                        ProfileScreen()
                    }
                }
            NavHost(
                navController = navController,
                graph = graph,
                modifier = Modifier.padding(innerPadding)
            )

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomnavTheme {
 //       Greeting("Android")
    }
}