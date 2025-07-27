package com.example.note.presentation.screen.home

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.note.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {

    private val _selectedTabIndex = mutableIntStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex
    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog

    fun onTabSelected(index: Int) {
        _selectedTabIndex.intValue = index
    }

    fun setShowDialog(value: Boolean) {
        _showDialog.value = value
    }

}