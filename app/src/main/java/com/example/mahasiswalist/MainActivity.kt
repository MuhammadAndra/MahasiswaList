package com.example.mahasiswalist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mahasiswalist.pages.HomePage
import com.example.mahasiswalist.pages.InsertUpdatePage
import com.example.mahasiswalist.pages.SearchPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "homepage") {
                composable("homepage"){ HomePage(navController = navController)}
                composable("insertupdatepage"){ InsertUpdatePage(navController = navController)}
                composable("searchpage"){ SearchPage(navController = navController)}
            }
        }
    }
}