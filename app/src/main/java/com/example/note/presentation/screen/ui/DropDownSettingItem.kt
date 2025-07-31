package com.example.note.presentation.screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropDownSettingItem(
    name: String,
    tint: Color,
    selectedSize: String,
    expanded: Boolean,
    list: List<String>,
    icon: Int,
    onClickSelectSize: (String) -> Unit,
    onToggle: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(vertical = 5.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Text(
                text = selectedSize,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .clickable { onToggle(true) }
                    .padding(15.dp)
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { onToggle(false) }) {
                list.forEach { size ->
                    CompositionLocalProvider(
                        LocalLayoutDirection provides LayoutDirection.Rtl
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    size,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 18.sp,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            onClick = {
                                onClickSelectSize(size)
                                onToggle(false)
                            }
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(end = 10.dp),
                fontSize = 20.sp
            )
            Icon(
                painter = painterResource(icon),
                null,
                modifier = Modifier.size(25.dp),
                tint = tint
            )
        }

    }
}