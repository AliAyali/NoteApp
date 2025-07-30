package com.example.note.presentation.screen.setting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.core.utils.nameToDisplay
import com.example.note.navigation.NavigationScreen


@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val themeState by viewModel.isDarkTheme
    var lockState by viewModel.statePassword
    var backupState by remember { mutableStateOf(false) }
    val fontSizes = FontSize.entries
    val themeColor = listOf(Color(0xFFFFC107), Color(0xFF4CAF50), Color(0xFFE91E63))
    val sortOptions = listOf(
        SortOrder.DATE_DESC to "جدیدترین",
        SortOrder.DATE_ASC to "تاریخ",
        SortOrder.TITLE to "نام"
    )
    var expandedFontSize by remember { mutableStateOf(false) }
    var expandedThemeColor by remember { mutableStateOf(false) }
    var expandedSort by remember { mutableStateOf(false) }
    var selectedFontSize by viewModel.selectedFontSize
    val selectedColor by viewModel.primaryColor
    var selectedSort by viewModel.selectedSortOrder

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
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
                fontSize = MaterialTheme.typography.titleLarge.fontSize * 1.2
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
                name = "سایز فونت",
                MaterialTheme.colorScheme.secondary,
                selectedSize = selectedFontSize.displayName,
                expanded = expandedFontSize,
                list = fontSizes.map { it.displayName },
                icon = R.drawable.font_size,
                onClickSelectSize = { selectedName ->
                    val selectedEnum = fontSizes.firstOrNull { it.displayName == selectedName }
                    selectedEnum?.let { viewModel.updateFontSize(it) }
                },
                onToggle = { expandedFontSize = it }
            )

            LineBox()

            DropDownSettingItem(
                "رنگ تم",
                MaterialTheme.colorScheme.primary,
                when (selectedColor) {
                    Color(0xFFFFC107) -> "زرد"
                    Color(0xFF4CAF50) -> "سبز"
                    Color(0xFFE91E63) -> "صورتی"
                    else -> "سفارشی"
                },
                expandedThemeColor,
                listOf("زرد", "سبز", "صورتی"),
                R.drawable.color,
                onClickSelectSize = { selectedName ->
                    val index = listOf("زرد", "سبز", "صورتی").indexOf(selectedName)
                    if (index >= 0) {
                        val color = themeColor[index]
                        viewModel.updatePrimaryColor(color)
                    }
                }
            ) {
                expandedThemeColor = it
            }


            LineBox()

            DropDownSettingItem(
                "مرتب سازی",
                MaterialTheme.colorScheme.secondary,
                selectedSort.nameToDisplay(),
                expandedSort,
                sortOptions.map { it.second },
                R.drawable.folder,
                onClickSelectSize = { selectedName ->
                    val selectedOrder = sortOptions.firstOrNull { it.second == selectedName }?.first
                    selectedOrder?.let {
                        viewModel.updateSortOrder(it)
                    }
                }
            ) {
                expandedSort = it
            }

            LineBox()

            var animatedVisibility by remember { mutableStateOf(false) }

            SwitchSetting(
                "قفل برنامه",
                R.drawable.lock,
                state = lockState,
            ) {
                lockState = it
                animatedVisibility = it
                viewModel.statePassword.value = false
                viewModel.updateStatePassword(false)
            }

            AnimatedVisibility(visible = animatedVisibility) {

                var lockSTateHeader by remember { mutableStateOf("") }
                var lockSTateFooter by remember { mutableStateOf("") }
                var passwordVisible by remember { mutableStateOf(false) }

                Column {

                    Text(
                        text = "توجه: لطفاً رمز عبور را به‌خاطر بسپارید. در صورت فراموشی،" +
                                " به دلیل مسائل امنیتی امکان دسترسی به برنامه وجود نخواهد داشت",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        textAlign = TextAlign.End
                    )

                    TextField(
                        value = lockSTateHeader,
                        onValueChange = { lockSTateHeader = it },
                        placeholder = {
                            Text(
                                text = "رمز را وارد کنید",
                                modifier = Modifier.fillMaxWidth(),
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
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        trailingIcon = {
                            val icon = if (passwordVisible) painterResource(R.drawable.visibility)
                            else painterResource(R.drawable.visibility_off)
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(painter = icon, contentDescription = "Visibility")
                            }
                        }
                    )

                    val passwordsMatch =
                        lockSTateHeader == lockSTateFooter || lockSTateFooter.isEmpty()

                    TextField(
                        value = lockSTateFooter,
                        onValueChange = { lockSTateFooter = it },
                        placeholder = {
                            Text(
                                text = "دوباره رمز را وارد کنید",
                                modifier = Modifier.fillMaxWidth(),
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
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.NumberPassword
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        isError = !passwordsMatch
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        enabled = lockSTateHeader.isNotBlank() &&
                                lockSTateFooter.isNotBlank() &&
                                passwordsMatch,
                        onClick = {
                            lockState = true
                            viewModel.updateStatePassword(true)
                            viewModel.updatePassword(lockSTateFooter)
                            viewModel.statePassword.value = true
                            animatedVisibility = false
                            navController.navigate(NavigationScreen.Lock.route) {
                                popUpTo(NavigationScreen.Setting.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            "ذخیره"
                        )
                    }

                }

            }

            LineBox()

            SwitchSetting(
                "پشتیبان گیری",
                R.drawable.backup,
                state = backupState,
            ) { backupState = it }
        }
        Spacer(Modifier.height(100.dp))
    }
}