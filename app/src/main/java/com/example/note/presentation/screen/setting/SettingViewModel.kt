package com.example.note.presentation.screen.setting

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.note.data.local.dataStore.SettingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingPreferences: SettingPreferences
) : ViewModel() {

    var isDarkTheme = mutableStateOf(false)
        private set

    init {
        settingPreferences.darkModeFlow
            .onEach { isDark ->
                isDarkTheme.value = isDark
            }
            .launchIn(viewModelScope)
    }

    fun toggleTheme(value: Boolean) {
        isDarkTheme.value = value
        viewModelScope.launch {
            settingPreferences.saveDarkMode(value)
        }
    }


}