package com.example.note.presentation.screen.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheetDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    itemId: Int,
    viewModel: TaskViewModel = hiltViewModel(),
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var title by remember { mutableStateOf("") }
    val allItems by viewModel.notes.collectAsState()
    if (itemId != -1)
        title = allItems[itemId].title

    if (showDialog) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState
        ) {

            Row {

                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = {
                        Text(
                            text = "یک کار برای خود ایجاد کنید",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Check",
                            tint = MaterialTheme.colorScheme.secondary
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
                        unfocusedTextColor = MaterialTheme.colorScheme.surface,
                    ),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                )

            }

            Button(
                onClick = {
                    if (itemId == -1) viewModel.insert(title = title) else viewModel.update()
                    title = ""
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                ),
                modifier = Modifier.padding(bottom = 20.dp, start = 20.dp),
                enabled = title.isNotBlank()
            ) {
                Text(
                    text = "انجام شد",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700
                )
            }

            Spacer(Modifier.height(20.dp))

        }
    }
}