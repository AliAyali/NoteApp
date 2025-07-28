package com.example.note.presentation.screen.home.note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.local.dataBase.entity.NoteEntity
import com.example.note.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository,
) : ViewModel() {

    private val _selectedNotes = mutableStateOf<Set<Int>>(emptySet())
    val selectedNotes: State<Set<Int>> = _selectedNotes

    fun toggleSelection(noteId: Int) {
        _selectedNotes.value = if (_selectedNotes.value.contains(noteId)) {
            _selectedNotes.value - noteId
        } else {
            _selectedNotes.value + noteId
        }
    }

    fun clearSelection() {
        _selectedNotes.value = emptySet()
    }

    fun isInSelectionMode(): Boolean = _selectedNotes.value.isNotEmpty()

    fun deleteSelectedNotes() {
        viewModelScope.launch {
            _selectedNotes.value.forEach { id ->
                repository.deleteNoteByIds(listOf(id))
            }
            clearSelection()
        }
    }


    private val _notes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notes: StateFlow<List<NoteEntity>> = _notes

    init {
        viewModelScope.launch {
            repository.getAllNotes().collect { noteList ->
                _notes.value = noteList
            }
        }
    }


}