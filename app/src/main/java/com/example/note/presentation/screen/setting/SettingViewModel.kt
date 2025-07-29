package com.example.note.presentation.screen.setting

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.local.dataStore.SettingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingPreferences: SettingPreferences,
) : ViewModel() {

    var selectedSortOrder = mutableStateOf(SortOrder.DATE_ASC)
        private set

    var isDarkTheme = mutableStateOf(false)
        private set

    var selectedFontSize = mutableStateOf(FontSize.MEDIUM)
        private set

    val primaryColor = mutableStateOf(Color(0xFFFFC107))

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

}