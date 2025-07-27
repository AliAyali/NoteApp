package com.example.note.presentation.screen.item

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.core.utils.PersianDate
import com.example.note.data.local.dataBase.entity.NoteEntity
import com.example.note.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: NoteRepository,
) : ViewModel() {
    private val _today = mutableStateOf<String>(PersianDate().getTodayPersianDate())
    val today: State<String> = _today
    fun insert(title: String, detail: String, date: String) {
        if (title.isBlank() && detail.isBlank()) return
        viewModelScope.launch {
            repository.insertNote(NoteEntity(title = title, detail = detail, date = date))
        }
    }
}