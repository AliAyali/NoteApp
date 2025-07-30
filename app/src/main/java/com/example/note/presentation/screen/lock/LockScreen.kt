package com.example.note.presentation.screen.lock

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun LockScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    Log.i("TAG", "LOCK")
    var passFake by remember { mutableStateOf("") }
    val reallyPass = viewModel.password.value
    var error by remember { mutableStateOf("") }

    val arrayOneRow = mutableListOf(1, 2, 3)
    val arrayTwoRow = mutableListOf(4, 5, 6)
    val arrayThreeRow = mutableListOf(7, 8, 9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.lock),
        )

        LottieAnimation(
            composition = composition,
            iterations = Integer.MAX_VALUE,
            modifier = Modifier
                .width(300.dp)
                .height(300.dp),
            isPlaying = true,
            alignment = Alignment.Center
        )

        Text(
            text = error, color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.W500
        )

        Spacer(Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            repeat(reallyPass.length) { index ->
                Box(
                    Modifier
                        .size(10.dp)
                        .background(
                            color = if (index < passFake.length) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                )
            }
        }

        Spacer(Modifier.height(30.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            repeat(3) {
                Button(
                    onClick = {
                        passFake += arrayOneRow[it].toString()
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
                    )
                ) {
                    Text(text = arrayOneRow[it].toString(), fontSize = 20.sp, modifier = Modifier.padding(10.dp))
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            repeat(3) {
                Button(
                    onClick = {
                        passFake += arrayTwoRow[it].toString()
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
                    )
                ) {
                    Text(text = arrayTwoRow[it].toString(), fontSize = 20.sp, modifier = Modifier.padding(10.dp))
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            repeat(3) {
                Button(
                    onClick = {
                        passFake += arrayThreeRow[it].toString()
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
                    )
                ) {
                    Text(text = arrayThreeRow[it].toString(), fontSize = 20.sp, modifier = Modifier.padding(10.dp))
                }
            }
        }

        Spacer(Modifier.height(20.dp))


        Button(
            onClick = {
                passFake += "0"
            }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
            )
        ) {
            Text(
                text = "0", fontSize = 20.sp, modifier = Modifier.padding(10.dp)
            )
        }

        LaunchedEffect(passFake) {
            if (reallyPass.isNotBlank() && passFake.length == reallyPass.length) {
                delay(300)
                if (passFake == reallyPass) {
                    navController.navigate(NavigationScreen.Home.route) {
                        popUpTo(NavigationScreen.Lock.route) { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    passFake = ""
                    error = "رمز اشتباه است"
                }
            }
        }


    }

}