package com.example.note.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note.data.local.dataBase.dao.NoteDao
import com.example.note.data.local.dataBase.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class MyDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "note_db"
        const val NOTE_TABLE = "note_table"
        const val TASK_TABLE = "task_table"
        const val DATABASE_VERSION = 1
    }
}