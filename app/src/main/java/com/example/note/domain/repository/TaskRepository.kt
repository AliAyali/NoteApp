package com.example.note.domain.repository

import com.example.note.data.local.dataBase.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task: TaskEntity)
    suspend fun deleteTasksByIds(ids: List<Int>)
    suspend fun updateTask(task: TaskEntity)
    suspend fun getTaskById(id: Int): TaskEntity?
    fun getAllTasks(): Flow<List<TaskEntity>>
}
