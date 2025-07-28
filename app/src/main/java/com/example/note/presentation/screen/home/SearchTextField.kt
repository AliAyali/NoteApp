package com.example.note.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    query: String,
    onQueryChange: (String) -> Unit,
    isNoteTab: Boolean
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = if (isNoteTab) "جست و جوی یادداشت ها" else "جست و جوی کارها",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Clear",
                modifier = Modifier.clickable { onQueryChange("") }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(10.dp)
            .clip(CircleShape),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.End,
            color = if (query.isNotBlank()) MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.secondary
        )
    )
}
