package com.example.note.presentation.screen.setting

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.local.dataBase.entity.NoteEntity
import com.example.note.data.local.dataStore.SettingPreferences
import com.example.note.domain.repository.NoteRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingPreferences: SettingPreferences,
    private val repository: NoteRepository,
) : ViewModel() {

    var selectedSortOrder = mutableStateOf(SortOrder.DATE_ASC)
        private set

    var isDarkTheme = mutableStateOf(false)
        private set

    var selectedFontSize = mutableStateOf(FontSize.MEDIUM)
        private set

    val primaryColor = mutableStateOf(Color(0xFFFFC107))
    val statePassword = mutableStateOf(false)
    val password = mutableStateOf("")

    init {
        settingPreferences.darkModeFlow
            .onEach { isDark ->
                isDarkTheme.value = isDark
            }
            .launchIn(viewModelScope)

        settingPreferences.fontSizeFlow
            .onEach { size ->
                selectedFontSize.value = size
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            settingPreferences.primaryColorFlow.collect {
                primaryColor.value = it
            }
        }
        settingPreferences.sortOrderFlow
            .onEach { sortOrder ->
                selectedSortOrder.value = sortOrder
            }
            .launchIn(viewModelScope)
        settingPreferences.passwordStateFlow
            .onEach { state ->
                statePassword.value = state
            }
            .launchIn(viewModelScope)

        settingPreferences.passwordFlow
            .onEach { pass ->
                password.value = pass
            }
            .launchIn(viewModelScope)
    }

    fun toggleTheme(value: Boolean) {
        isDarkTheme.value = value
        viewModelScope.launch {
            settingPreferences.saveDarkMode(value)
        }
    }

    fun updateFontSize(size: FontSize) {
        viewModelScope.launch {
            settingPreferences.saveFontSize(size)
        }
    }

    fun updatePrimaryColor(color: Color) {
        viewModelScope.launch {
            primaryColor.value = color
            settingPreferences.savePrimaryColor(color)
        }
    }

    fun updateSortOrder(sortOrder: SortOrder) {
        viewModelScope.launch {
            selectedSortOrder.value = sortOrder
            settingPreferences.saveSortOrder(sortOrder)
        }
    }

    fun updateStatePassword(state: Boolean) {
        viewModelScope.launch {
            statePassword.value = state
            settingPreferences.saveStatePassword(state)
        }
    }

    fun updatePassword(password: String) {
        viewModelScope.launch {
            settingPreferences.savePassword(password)
        }
    }

    fun backupToJsonFile(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val notesList = repository.getAllNotes().first()
                val gson = Gson()
                val jsonString = gson.toJson(notesList)

                val fileName = "note_backup.json"
                val file = File(context.filesDir, fileName)

                file.writeText(jsonString)

                Log.d("Backup", "پشتیبان‌گیری موفق}")
            } catch (_: Exception) {
                Log.e("Backup", "خطا در پشتیبان‌گیری")
            }
        }
    }


    fun restoreFromJsonFile(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fileName = "note_backup.json"
                val file = File(context.filesDir, fileName)

                if (!file.exists()) {
                    Log.e("Restore", "فایل پشتیبان پیدا نشد")
                    return@launch
                }

                val jsonString = file.readText()
                val notesArray = Gson().fromJson(jsonString, Array<NoteEntity>::class.java)
                val notesList = notesArray.toList()

                for (note in notesList) {
                    repository.insertNote(note)
                }

                Log.d("Restore", "true")
            } catch (_: Exception) {
                Log.e("Restore", "خطا در بازیابی")
            }
        }
    }



}