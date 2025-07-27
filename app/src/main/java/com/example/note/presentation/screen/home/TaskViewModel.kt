package com.example.note.presentation.screen.home

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

    private val _notes = MutableStateFlow<List<TaskEntity>>(emptyList())
    val notes: StateFlow<List<TaskEntity>> = _notes

    private val _selectedTabIndex = mutableIntStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex
    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog
    private val _id = mutableIntStateOf(0)
    val id: State<Int> = _id
    private val _title = mutableStateOf("")
    val title: State<String> = _title
    private val _isChecked = mutableStateOf(false)
    val isChecked: State<Boolean> = _isChecked

    init {
        viewModelScope.launch {
            repository.getAllTasks().collect { taskList ->
                _notes.value = taskList
            }
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

    fun update() {
        if (_title.value.isBlank()) return
        viewModelScope.launch {
            repository.updateTask(
                TaskEntity(
                    id = id.value,
                    title = _title.value,
                    isChecked = isChecked.value
                )
            )
        }
    }

}