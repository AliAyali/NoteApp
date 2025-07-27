package com.example.note.data.local.dataBase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.note.data.local.dataBase.MyDataBase

@Entity(tableName = MyDataBase.TASK_TABLE)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "title") var title: Int,
    @ColumnInfo(name = "idChecked") var idChecked: Boolean,
)