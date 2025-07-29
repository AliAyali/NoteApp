package com.example.note.presentation.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.navigation.NavigationScreen

@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val themeState by viewModel.isDarkTheme
    var lockState by remember { mutableStateOf(false) }
    var backupState by remember { mutableStateOf(false) }
    val fontSizes = listOf("کوچک", "متوسط", "بزرگ")
    val themeColor = listOf("زرد", "سبز", "صورتی")
    val sort = listOf("جدیدترین", "تاریخ", "نام")
    var expandedFontSize by remember { mutableStateOf(false) }
    var expandedThemeColor by remember { mutableStateOf(false) }
    var expandedSort by remember { mutableStateOf(false) }
    var selectedSize by remember { mutableStateOf(fontSizes[1]) }
    var selectedColor by remember { mutableStateOf(themeColor[1]) }
    var selectedSort by remember { mutableStateOf(sort[1]) }

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                "ArrowBack",
                modifier = Modifier
                    .clickable {
                        navController.navigate(NavigationScreen.Home.route) {
                            popUpTo(NavigationScreen.Home.route) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                    .size(30.dp)
            )
            Text(
                text = "تنظیمات",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontSize = 25.sp,
                fontWeight = FontWeight.W500
            )
        }

        Card(
            modifier = Modifier.padding(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {

            SwitchSetting(
                "تم تاریک",
                R.drawable.moon,
                state = themeState,
            ) {
                viewModel.toggleTheme(it)
            }

            LineBox()

            DropDownSettingItem(
                "سایز فونت",
                selectedSize,
                expandedFontSize,
                fontSizes,
                R.drawable.font_size,
                onClickSelectSize = {
                    selectedSize = it
                }
            ) {
                expandedFontSize = it
            }

            LineBox()

            DropDownSettingItem(
                "رنگ تم",
                selectedColor,
                expandedThemeColor,
                themeColor,
                R.drawable.color,
                onClickSelectSize = {
                    selectedColor = it
                }
            ) {
                expandedThemeColor = it
            }

            LineBox()

            DropDownSettingItem(
                "مرتب سازی",
                selectedSort,
                expandedSort,
                sort,
                R.drawable.folder,
                onClickSelectSize = {
                    selectedSort = it
                }
            ) {
                expandedSort = it
            }

            LineBox()

            SwitchSetting(
                "قفل برنامه",
                R.drawable.lock,
                state = lockState,
            ) { lockState = it }

            LineBox()

            SwitchSetting(
                "پشتیبان گیری",
                R.drawable.backup,
                state = backupState,
            ) { backupState = it }


        }
    }
}