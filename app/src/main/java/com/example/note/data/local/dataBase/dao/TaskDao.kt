package com.example.note.data.local.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note.data.local.dataBase.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Query("DELETE FROM task_table WHERE id IN (:ids)")
    suspend fun deleteTasksByIds(ids: List<Int>)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("UPDATE task_table SET isChecked = :isChecked WHERE id = :id")
    suspend fun updateIsChecked(id: Int, isChecked: Boolean)

    @Query("SELECT * FROM task_table WHERE isChecked = :checked ORDER BY id DESC")
    fun getTasksByChecked(checked: Boolean): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_table WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Int): TaskEntity?

}