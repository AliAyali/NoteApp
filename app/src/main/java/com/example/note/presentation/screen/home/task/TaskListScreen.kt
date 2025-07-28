package com.example.note.presentation.screen.home.task

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.note.R
import com.example.note.data.local.dataBase.entity.TaskEntity
import com.example.note.presentation.screen.home.EmptyScreen
import com.example.note.presentation.screen.home.task.TaskViewModel

@Composable
fun TaskListScreen(
    filtersItemsTaskFalse: List<TaskEntity>,
    filtersItemsTaskTrue: List<TaskEntity>,
    visibility: Boolean,
    onVisibilityChange: (Boolean) -> Unit,
    taskViewModel: TaskViewModel
) {
    if (filtersItemsTaskFalse.isEmpty() && filtersItemsTaskTrue.isEmpty()) {
        EmptyScreen(
            iconRes = R.drawable.ic_add_task,
            message = "هیچ کاری موجود نیست"
        )
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            if (filtersItemsTaskFalse.isNotEmpty()) {
                items(filtersItemsTaskFalse, key = { it.id }) { item ->
                    TaskItem(
                        title = item.title,
                        state = item.isChecked,
                        isSelected = taskViewModel.selectedTasks.value.contains(item.id),
                        modifier = Modifier.pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    if (taskViewModel.isInSelectionMode())
                                        taskViewModel.toggleSelection(item.id)
                                    else {
                                        taskViewModel.changeId(item.id)
                                        taskViewModel.setShowDialog(true)
                                    }
                                },
                                onLongPress = {
                                    taskViewModel.toggleSelection(item.id)
                                }
                            )
                        }
                    ) {
                        taskViewModel.toggleCheck(item)
                    }
                }
            }

            if (filtersItemsTaskTrue.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "کامل شده ${filtersItemsTaskTrue.size}",
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.clickable {
                                onVisibilityChange(!visibility)
                            }
                        )
                        Icon(
                            if (visibility) Icons.Default.KeyboardArrowUp
                            else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                items(filtersItemsTaskTrue, key = { it.id }) { item ->
                    AnimatedVisibility(visible = visibility) {
                        TaskItem(
                            title = item.title,
                            state = item.isChecked,
                            isSelected = taskViewModel.selectedTasks.value.contains(item.id),
                            modifier = Modifier.pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        if (taskViewModel.isInSelectionMode())
                                            taskViewModel.toggleSelection(item.id)
                                    },
                                    onLongPress = {
                                        taskViewModel.toggleSelection(item.id)
                                    }
                                )
                            }
                        ) {
                            taskViewModel.toggleCheck(item)
                        }
                    }
                }
            }
        }
    }
}
