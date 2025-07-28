package com.example.note.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.presentation.screen.home.note.NoteViewModel
import com.example.note.presentation.screen.home.task.TaskViewModel

@Composable
fun TopBarContent(
    selectedTabIndex: Int,
    taskViewModel: TaskViewModel,
    noteViewModel: NoteViewModel,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (selectedTabIndex) {
            0 -> {
                if (taskViewModel.isInSelectionMode()) {
                    Row {
                        IconButton(onClick = { taskViewModel.clearSelection() }) {
                            Icon(Icons.Default.Close, contentDescription = "لغو انتخاب")
                        }
                        IconButton(onClick = { taskViewModel.deleteSelectedTasks() }) {
                            Icon(Icons.Default.Delete, contentDescription = "حذف")
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.width(96.dp))
                }

                Text(
                    modifier = Modifier.padding(end = 20.dp),
                    text = if (taskViewModel.isInSelectionMode()) {
                        "${taskViewModel.selectedTasks.value.size} انتخاب شده"
                    } else {
                        "کارها"
                    },
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Medium,
                    fontSize = 25.sp
                )
            }

            1 -> {
                if (noteViewModel.isInSelectionMode()) {
                    Row {
                        IconButton(onClick = { noteViewModel.clearSelection() }) {
                            Icon(Icons.Default.Close, contentDescription = "لغو انتخاب")
                        }
                        IconButton(onClick = { noteViewModel.deleteSelectedNotes() }) {
                            Icon(Icons.Default.Delete, contentDescription = "حذف")
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.width(96.dp))
                }

                Text(
                    modifier = Modifier.padding(end = 20.dp),
                    text = if (noteViewModel.isInSelectionMode()) {
                        "${noteViewModel.selectedNotes.value.size} انتخاب شده"
                    } else {
                        "یادداشت‌ها"
                    },
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Medium,
                    fontSize = 25.sp
                )
            }
        }
    }
}
