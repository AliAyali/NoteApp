package com.example.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.note.navigation.NavigationScreen
import com.example.note.navigation.SetupNavigation
import com.example.note.ui.theme.NoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NoteTheme {
                val navController = rememberNavController()
                Scaffold(

                    floatingActionButton = {
                        FloatingActionButton(
                            modifier = Modifier.padding(10.dp),
                            onClick = {
                                navController.navigate(NavigationScreen.Item.route)
                            },
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ) {

                            Icon(
                                Icons.Default.Add,
                                "Add"
                            )

                        }
                    },
                    floatingActionButtonPosition = FabPosition.Start
                ) { padding ->
                    SetupNavigation(padding, navController)
                }
            }
        }
    }
}