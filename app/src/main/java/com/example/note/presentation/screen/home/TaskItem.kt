package com.example.note.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskItem(
    title: String,
    modifier: Modifier = Modifier,
    state: Boolean = false,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    action: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.inverseSurface
            else MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(10.dp)
            ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        var stateCheckBox by remember { mutableStateOf(state) }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 10.dp, top = 10.dp),
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Checkbox(
                checked = stateCheckBox,
                onCheckedChange = {
                    action()
                },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = MaterialTheme.colorScheme.secondary
                )
            )
        }

    }
}