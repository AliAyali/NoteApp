package com.example.note.presentation.screen.home.note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.local.dataBase.entity.NoteEntity
import com.example.note.data.local.dataStore.SettingPreferences
import com.example.note.domain.repository.NoteRepository
import com.example.note.presentation.screen.setting.SortOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    settingPreferences: SettingPreferences,
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

    @OptIn(ExperimentalCoroutinesApi::class)
    val notes: StateFlow<List<NoteEntity>> = settingPreferences.sortOrderFlow
        .flatMapLatest { sortOrder ->
            when (sortOrder) {
                SortOrder.TITLE -> repository.getNotesOrderByTitle()
                SortOrder.DATE_ASC -> repository.getNotesOrderByDateAsc()
                SortOrder.DATE_DESC -> repository.getNotesOrderByDateDesc()
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

}