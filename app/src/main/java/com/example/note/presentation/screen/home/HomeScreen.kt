package com.example.note.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.presentation.screen.home.note.NoteListScreen
import com.example.note.presentation.screen.home.note.NoteViewModel
import com.example.note.presentation.screen.home.task.TaskListScreen
import com.example.note.presentation.screen.home.task.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    taskViewModel: TaskViewModel = hiltViewModel(),
    noteViewModel: NoteViewModel = hiltViewModel(),
) {
    var selectedTabIndex by remember { mutableIntStateOf(1) }
    var query by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(true) }

    val selectedNotes by noteViewModel.selectedNotes

    val allItemsNote by noteViewModel.notes.collectAsState()
    val allItemsTaskTrue by taskViewModel.notesTrue.collectAsState()
    val allItemsTaskFalse by taskViewModel.notesFalse.collectAsState()

    val filteredItemsNote = remember(query, allItemsNote) {
        if (query.isBlank()) allItemsNote else allItemsNote.filter {
            it.title.contains(query, ignoreCase = true)
                    || it.detail.contains(query, ignoreCase = true)
                    || it.date.contains(query, ignoreCase = true)
        }
    }

    val filteredItemsTaskTrue = remember(query, allItemsTaskTrue) {
        if (query.isBlank()) allItemsTaskTrue
        else allItemsTaskTrue.filter { it.title.contains(query, ignoreCase = true) }
    }

    val filteredItemsTaskFalse = remember(query, allItemsTaskFalse) {
        if (query.isBlank()) allItemsTaskFalse
        else allItemsTaskFalse.filter { it.title.contains(query, ignoreCase = true) }
    }

    MyBottomSheetDialog(
        showDialog = taskViewModel.showDialog.value,
        onDismiss = { taskViewModel.setShowDialog(false) },
        itemId = taskViewModel.id.value
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        TopBarWithTabs(
            selectedTabIndex,
            onTabSelected = { selectedTabIndex = it },
            navController
        )

        TopBarContent(
            selectedTabIndex,
            taskViewModel,
            noteViewModel
        )

        SearchTextField(
            query = query,
            onQueryChange = { query = it },
            isNoteTab = selectedTabIndex == 1
        )

        when (selectedTabIndex) {
            0 -> {
                taskViewModel.onTabSelected(0)
                TaskListScreen(
                    filtersItemsTaskFalse = filteredItemsTaskFalse,
                    filtersItemsTaskTrue = filteredItemsTaskTrue,
                    visibility = visibility,
                    onVisibilityChange = { visibility = it },
                    taskViewModel = taskViewModel
                )
            }

            1 -> {
                taskViewModel.onTabSelected(1)
                NoteListScreen(
                    filteredItemsNote = filteredItemsNote,
                    selectedNotes = selectedNotes,
                    navController = navController,
                    noteViewModel = noteViewModel
                )
            }
        }
    }
}

