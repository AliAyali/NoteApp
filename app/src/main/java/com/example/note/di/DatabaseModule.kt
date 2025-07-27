package com.example.note.di

import android.content.Context
import androidx.room.Room
import com.example.note.data.local.dataBase.MyDataBase
import com.example.note.data.local.dataBase.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): MyDataBase {
        return Room.databaseBuilder(
            context,
            MyDataBase::class.java,
            MyDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideNoteDao(database: MyDataBase): NoteDao {
        return database.noteDao()
    }

}