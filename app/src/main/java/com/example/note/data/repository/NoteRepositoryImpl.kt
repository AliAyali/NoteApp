package com.example.note.data.repository

import com.example.note.data.local.dataBase.dao.NoteDao
import com.example.note.data.local.dataBase.entity.NoteEntity
import com.example.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
) : NoteRepository {

    override suspend fun insertNote(note: NoteEntity) {
        dao.insertNote(note)
    }

    override suspend fun deleteNoteByIds(noteId: List<Int>) {
        dao.deleteNotesByIds(noteId)
    }

    override suspend fun updateNote(note: NoteEntity) {
        dao.updateNote(note)
    }

    override suspend fun getNoteById(id: Int): NoteEntity? = dao.getNoteById(id)

    override fun getAllNotes(): Flow<List<NoteEntity>> = dao.getAllNotes()

    override fun getNotesOrderByTitle(): Flow<List<NoteEntity>> =
        dao.getNotesOrderByTitle()


    override fun getNotesOrderByDateAsc(): Flow<List<NoteEntity>> =
        dao.getNotesOrderByDateAsc()


    override fun getNotesOrderByDateDesc(): Flow<List<NoteEntity>> =
        dao.getNotesOrderByDateDesc()
}