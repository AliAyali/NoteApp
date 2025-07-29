package com.example.note.data.local.dataStore

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.note.presentation.screen.setting.FontSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingPreferences @Inject constructor(
    private val context: Context,
) {

    companion object {
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        val FONT_SIZE_KEY = stringPreferencesKey("font_size")
        private val PRIMARY_COLOR_KEY = intPreferencesKey("primary_color")
        private val DEFAULT_COLOR = Color(0xFFFFC107)
    }

    suspend fun saveDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }

    val darkModeFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }


    val fontSizeFlow: Flow<FontSize> = context.dataStore.data
        .map { preferences ->
            val sizeName = preferences[FONT_SIZE_KEY] ?: FontSize.MEDIUM.name
            FontSize.valueOf(sizeName)
        }

    suspend fun saveFontSize(fontSize: FontSize) {
        context.dataStore.edit { settings ->
            settings[FONT_SIZE_KEY] = fontSize.name
        }
    }

    val primaryColorFlow: Flow<Color> = context.dataStore.data
        .map { preferences ->
            val colorInt = preferences[PRIMARY_COLOR_KEY] ?: DEFAULT_COLOR.toArgb()
            Color(colorInt)
        }

    suspend fun savePrimaryColor(color: Color) {
        context.dataStore.edit { preferences ->
            preferences[PRIMARY_COLOR_KEY] = color.toArgb()
        }
    }
}
