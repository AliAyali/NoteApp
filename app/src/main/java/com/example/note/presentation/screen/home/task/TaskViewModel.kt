package com.example.note.presentation.screen.home.task

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.local.dataBase.entity.TaskEntity
import com.example.note.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {

    private val _notesFalse = MutableStateFlow<List<TaskEntity>>(emptyList())
    val notesFalse: StateFlow<List<TaskEntity>> = _notesFalse

    private val _notesTrue = MutableStateFlow<List<TaskEntity>>(emptyList())
    val notesTrue: StateFlow<List<TaskEntity>> = _notesTrue

    private val _selectedTabIndex = mutableIntStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex
    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog
    private val _id = mutableIntStateOf(-1)
    val id: State<Int> = _id
    private val _title = mutableStateOf("")
    val title: State<String> = _title
    private val _isChecked = mutableStateOf(false)
    val isChecked: State<Boolean> = _isChecked

    val selectedTasks = mutableStateOf(setOf<Int>())

    init {
        viewModelScope.launch {
            launch {
                repository.getTasksByChecked(false).collect { taskList ->
                    _notesFalse.value = taskList
                }
            }
            launch {
                repository.getTasksByChecked(true).collect { taskList ->
                    _notesTrue.value = taskList
                }
            }
        }
    }


    fun toggleCheck(task: TaskEntity) {
        viewModelScope.launch {
            Log.d("TOGGLE", "Clicked on: ${task.id}, current isChecked: ${task.isChecked}")
            repository.updateIsChecked(task.id, !task.isChecked)
        }
    }

    fun onTabSelected(index: Int) {
        _selectedTabIndex.intValue = index
    }

    fun setShowDialog(value: Boolean) {
        _showDialog.value = value
    }

    fun insert(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.insertTask(
                TaskEntity(
                    title = title,
                    isChecked = false
                )
            )
        }
    }

    fun update(id: Int, title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.updateTask(
                TaskEntity(
                    id = id,
                    title = title,
                    isChecked = isChecked.value
                )
            )
        }
    }

    fun toggleSelection(taskId: Int) {
        selectedTasks.value = selectedTasks.value.toMutableSet().apply {
            if (contains(taskId)) remove(taskId) else add(taskId)
        }
    }

    fun isInSelectionMode(): Boolean = selectedTasks.value.isNotEmpty()

    fun clearSelection() {
        selectedTasks.value = emptySet()
    }

    fun deleteSelectedTasks() {
        viewModelScope.launch {
            selectedTasks.value.forEach { id ->
                repository.deleteTasksByIds(listOf(id))
            }
            clearSelection()
        }
    }

    fun changeId(id: Int) {
        _id.intValue = id
    }

}