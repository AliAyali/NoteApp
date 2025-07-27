package com.example.note.data.repository

import com.example.note.data.local.dataBase.dao.TaskDao
import com.example.note.data.local.dataBase.entity.TaskEntity
import com.example.note.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao,
) : TaskRepository {

    override suspend fun insertTask(task: TaskEntity) {
        dao.insertTask(task)
    }

    override suspend fun deleteTasksByIds(ids: List<Int>) {
        dao.deleteTasksByIds(ids)
    }

    override suspend fun updateTask(task: TaskEntity) {
        dao.updateTask(task)
    }

    override suspend fun getTaskById(id: Int): TaskEntity? =
        dao.getTaskById(id)

    override fun getAllTasks(): Flow<List<TaskEntity>> =
        dao.getAllTasks()
}