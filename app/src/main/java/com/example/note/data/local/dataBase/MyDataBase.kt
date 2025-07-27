package com.example.note.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note.data.local.dataBase.MyDataBase.Companion.DATABASE_VERSION
import com.example.note.data.local.dataBase.dao.NoteDao
import com.example.note.data.local.dataBase.dao.TaskDao
import com.example.note.data.local.dataBase.entity.NoteEntity
import com.example.note.data.local.dataBase.entity.TaskEntity

@Database(
    entities = [
        NoteEntity::class,
        TaskEntity::class
    ],
    version = DATABASE_VERSION
)
abstract class MyDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun taskDao(): TaskDao

    companion object {
        const val DATABASE_NAME = "note_db"
        const val NOTE_TABLE = "note_table"
        const val TASK_TABLE = "task_table"
        const val DATABASE_VERSION = 1
    }
}