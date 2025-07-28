package com.example.note.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.navigation.NavigationScreen

@Composable
fun TopBarWithTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    navController: NavController,
) {
    val tabs = listOf(
        painterResource(R.drawable.ic_task),
        painterResource(R.drawable.ic_note)
    )

    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Setting",
            modifier = Modifier
                .padding(12.dp)
                .clickable {
                    navController.navigate(NavigationScreen.Setting.route)
                }
        )
        Spacer(Modifier.width(50.dp))
        TabRow(selectedTabIndex = selectedTabIndex, indicator = {}, divider = {}) {
            tabs.forEachIndexed { index, icon ->
                val isSelected = selectedTabIndex == index
                Tab(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    selected = isSelected,
                    onClick = { onTabSelected(index) },
                    icon = {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = icon,
                            contentDescription = null,
                            tint = if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.secondary
                        )
                    }
                )
            }
        }
    }
}
