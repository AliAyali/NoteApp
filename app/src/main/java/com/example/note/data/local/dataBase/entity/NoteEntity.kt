package com.example.note.data.local.dataBase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.note.data.local.dataBase.MyDataBase

@Entity(tableName = MyDataBase.NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "detail") val detail: String,
    @ColumnInfo(name = "date") val date: String,
)