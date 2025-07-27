package com.example.note.data.repository

import com.example.note.data.local.dataBase.dao.NoteDao
import com.example.note.data.local.dataBase.entity.NoteEntity
import com.example.note.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
) : NoteRepository {

    override suspend fun insertNote(note: NoteEntity) {
        dao.insertNote(note)
    }

    override suspend fun deleteNoteById(noteId: Int) {
        dao.deleteNoteById(noteId)
    }

    override suspend fun updateNote(note: NoteEntity) {
        dao.updateNote(note)
    }

    override suspend fun getNoteById(id: Int): NoteEntity? = dao.getNoteById(id)

    override suspend fun getAllNotes(): List<NoteEntity> = dao.getAllNotes()

}