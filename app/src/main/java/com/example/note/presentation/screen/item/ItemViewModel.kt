package com.example.note.presentation.screen.item

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
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

    private val _id = mutableIntStateOf(0)
    val id: State<Int> = _id
    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _detail = mutableStateOf("")
    val detail: State<String> = _detail

    private val _date = mutableStateOf(PersianDate().getTodayPersianDate())
    val date: State<String> = _date

    private val _note = mutableStateOf<NoteEntity?>(null)
    val note: State<NoteEntity?> = _note

    fun insert() {
        if (_title.value.isBlank() && _detail.value.isBlank()) return
        viewModelScope.launch {
            repository.insertNote(
                NoteEntity(
                    title = _title.value,
                    detail = _detail.value,
                    date = _date.value
                )
            )
        }
    }

    fun update() {
        if (_title.value.isBlank() && _detail.value.isBlank()) return
        viewModelScope.launch {
            repository.updateNote(
                NoteEntity(
                    id = id.value,
                    title = _title.value,
                    detail = _detail.value,
                    date = _date.value
                )
            )
        }
    }

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            val result = repository.getNoteById(id)
            _note.value = result
            _id.intValue = result?.id ?: 0
            _title.value = result?.title ?: ""
            _detail.value = result?.detail ?: ""
            _date.value = result?.date ?: PersianDate().getTodayPersianDate()
        }
    }

    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun onDetailChange(newDetail: String) {
        _detail.value = newDetail
    }
}
