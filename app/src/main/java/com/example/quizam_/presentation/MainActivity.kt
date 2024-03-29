package com.example.quizam_.presentation

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizam_.R
import com.example.quizam_.presentation.card_list.CardListScreen
import com.example.quizam_.presentation.category_list.CategoryListScreen
import com.example.quizam_.presentation.game_leaderboard.GameLeaderBoardScreen
import com.example.quizam_.presentation.game_result.ResultScreen
import com.example.quizam_.presentation.game_start.GameStartScreen
import com.example.quizam_.presentation.ui.theme.Quizam_Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Quizam_Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    Navigation()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController)
        }
        composable(Screen.GameStartScreen.route) {
            GameStartScreen(navController)
        }
        composable(Screen.CategoryListScreen.route) {
            CategoryListScreen(navController)
        }
        composable(
            route = Screen.CardListScreen.route + "/{category}"
        ) {
            CardListScreen(navController)
        }
        composable(Screen.GameResultScreen.route) {
            ResultScreen(navController)
        }
        composable(Screen.GameLeaderBoardScreen.route) {
            GameLeaderBoardScreen(navController)
        }

    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(0.2f).getInterpolation(it)
                }
            )
        )
        delay(1000L)
        navController.navigate(Screen.GameStartScreen.route)

    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_red))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_quizam_logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}

