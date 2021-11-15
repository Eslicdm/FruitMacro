package com.eslirodrigues.fruitmacro

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.fruitmacro.screen.FruitScreen
import com.eslirodrigues.fruitmacro.ui.theme.DarkMagenta
import com.eslirodrigues.fruitmacro.ui.theme.FruitMacroTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FruitMacroTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash_screen"
                ) {
                    composable(route = "fruit_screen") {
                        FruitScreen()
                    }
                    composable(route = "splash_screen") {
                        SplashScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1500L)
        navController.navigate("fruit_screen") {
            popUpTo("splash_screen") {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier
            .background(DarkMagenta)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.scale(scale.value),
            painter = painterResource(id = R.drawable.ic_fruitmacro),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}

