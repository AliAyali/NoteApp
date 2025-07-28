package com.example.note.presentation.screen.home.note

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.data.local.dataBase.entity.NoteEntity
import com.example.note.navigation.NavigationScreen
import com.example.note.presentation.screen.home.EmptyScreen
import com.example.note.presentation.screen.home.note.NoteViewModel

@Composable
fun NoteListScreen(
    filteredItemsNote: List<NoteEntity>,
    selectedNotes: Set<Int>,
    navController: NavController,
    noteViewModel: NoteViewModel
) {
    if (filteredItemsNote.isNotEmpty()) {
        LazyColumn {
            items(filteredItemsNote, key = { it.id }) { noteEntity ->
                NoteItem(
                    title = noteEntity.title,
                    detail = noteEntity.detail,
                    date = noteEntity.date,
                    isSelected = selectedNotes.contains(noteEntity.id),
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                if (noteViewModel.isInSelectionMode()) {
                                    noteViewModel.toggleSelection(noteEntity.id)
                                } else {
                                    navController.navigate(NavigationScreen.Item.createRoute(noteEntity.id))
                                }
                            },
                            onLongPress = {
                                noteViewModel.toggleSelection(noteEntity.id)
                            }
                        )
                    }
                )
            }
        }
    } else {
        EmptyScreen(
            iconRes = R.drawable.ic_add_notes,
            message = "هیچ یادداشتی موجود نیست"
        )
    }
}
