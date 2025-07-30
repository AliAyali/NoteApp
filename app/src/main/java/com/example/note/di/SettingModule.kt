package com.example.note.di

import android.content.Context
import com.example.note.data.local.dataStore.SettingPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingModule {

    @Singleton
    @Provides
    fun provideSettingPreferences(
        @ApplicationContext context: Context,
    ): SettingPreferences {
        return SettingPreferences(context)
    }

}