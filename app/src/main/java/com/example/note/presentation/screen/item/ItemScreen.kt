package com.example.note.presentation.screen.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.navigation.NavigationScreen

@Composable
fun ItemScreen(
    navController: NavController,
    itemId: Int,
    viewModel: ItemViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LaunchedEffect(itemId) {
            if (itemId != -1) {
                viewModel.getNoteById(itemId)
            }
        }

        val title by viewModel.title
        val detail by viewModel.detail
        val date by viewModel.date

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        navController.navigate(NavigationScreen.Home.route) {
                            popUpTo(NavigationScreen.Home.route) { inclusive = true }
                        }
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.Done,
                contentDescription = "Done",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        if (itemId == -1) viewModel.insert() else viewModel.update()
                        navController.navigate(NavigationScreen.Home.route) {
                            popUpTo(NavigationScreen.Home.route) { inclusive = true }
                        }
                    }
            )
        }

        TextField(
            value = title,
            onValueChange = { viewModel.onTitleChange(it) },
            placeholder = {
                Text(
                    text = "عنوان",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Normal
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
            ),
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.End,
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                "نویسه ${detail.length}",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 15.sp
            )
            Text(
                "|",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 15.sp
            )
            Text(
                date,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 15.sp
            )
        }

        TextField(
            value = detail,
            onValueChange = { viewModel.onDetailChange(it) },
            placeholder = {
                Text(
                    text = "تایپ کردن را آغاز کنید",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    textAlign = TextAlign.End,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Normal
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
            ),
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.End,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
        )

    }
}