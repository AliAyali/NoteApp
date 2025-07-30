package com.example.note.presentation.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.note.R
import com.example.note.navigation.NavigationScreen
import com.example.note.presentation.screen.setting.SettingViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.note_splash),
        )
        LottieAnimation(
            composition = composition,
            iterations = Integer.MAX_VALUE,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            isPlaying = true,
            alignment = Alignment.Center
        )
        Spacer(Modifier.height(100.dp))
        CircularProgressIndicator()
    }


    LaunchedEffect(Unit) {
        while (viewModel.password.value.isEmpty() && viewModel.statePassword.value) {
            delay(100)
        }
        delay(3000)
        if (viewModel.statePassword.value)
            navController.navigate(NavigationScreen.Lock.route) {
                popUpTo(NavigationScreen.Splash.route) { inclusive = true }
                launchSingleTop = true
            }
        else
            navController.navigate(NavigationScreen.Home.route) {
                popUpTo(NavigationScreen.Splash.route) { inclusive = true }
                launchSingleTop = true
            }

    }


}