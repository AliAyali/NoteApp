package com.example.note.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteItem(title: String, detail: String, date: String) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(10.dp)
            ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {

        Text(
            modifier = Modifier
                .padding(end = 10.dp, top = 10.dp)
                .align(Alignment.End),
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.End),
            text = detail
        )
        Text(
            date,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp)
        )

    }
}