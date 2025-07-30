package com.example.note.presentation.screen.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AlertDialogBackup(
    title: String,
    text: String,
    disable: (Boolean) -> Unit,
    enable: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { disable(false) },
        confirmButton = {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextButton(onClick = {
                    enable()
                    disable(false)
                }) {
                    Text(
                        text = "بله",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                TextButton(onClick = {
                    disable(false)
                }) {
                    Text(
                        text = "خیر",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        text = {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        shape = RoundedCornerShape(10.dp),
    )
}