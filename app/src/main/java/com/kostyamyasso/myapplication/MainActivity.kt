package com.kostyamyasso.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kostyamyasso.myapplication.screen.restaraunts.RestaurantScreen
import com.kostyamyasso.myapplication.screen.restaraunts.RestaurantViewModel
import com.kostyamyasso.myapplication.screen.restaraunts.detail.DetailScreen
import com.kostyamyasso.myapplication.screen.sign_in.SignInScreen
import com.kostyamyasso.myapplication.screen.sign_up.SignUpScreen
import com.kostyamyasso.myapplication.screen.sign_up.SignUpScreenViewModel
import com.kostyamyasso.myapplication.sign_in.SignInScreenViewModel
import com.kostyamyasso.myapplication.ui.theme.AndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            AndroidAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "sign_up",
                    ) {
                        composable("sign_in") {
                            val signInViewModel = hiltViewModel<SignInScreenViewModel>()
                            SignInScreen(
                                signInScreenViewModel = signInViewModel,
                                navController = navController
                            )
                        }

                        composable("sign_up") {
                            val signUpViewModel = hiltViewModel<SignUpScreenViewModel>()
                            SignUpScreen(
                                signUpScreenViewModel = signUpViewModel,
                                navController = navController
                            )
                        }

                        composable("restaurants") {
                            val restaurantViewModel = hiltViewModel<RestaurantViewModel>()
                            RestaurantScreen(
                                restaurantViewModel = restaurantViewModel,
                                navController = navController
                            )
                        }

                        composable("detail/{rest_data}") { backStackEntry ->
                            DetailScreen(backStackEntry.arguments?.getString("rest_data").orEmpty())
                        }
                    }

                }
            }
        }
    }
}

