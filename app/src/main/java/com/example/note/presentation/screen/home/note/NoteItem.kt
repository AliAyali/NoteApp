package com.example.note.presentation.screen.home.note

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteItem(
    title: String,
    detail: String,
    date: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.inverseSurface
            else
                MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(10.dp)
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
            text = if (detail.length > 10) "..." + detail.take(10) else detail,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            date,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp)
        )

    }
}
