package com.example.note.domain.repository

import com.example.note.data.local.dataBase.entity.NoteEntity

interface NoteRepository {
    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNoteById(noteId: Int)
    suspend fun updateNote(note: NoteEntity)
    suspend fun getNoteById(id: Int): NoteEntity?
    suspend fun getAllNotes(): List<NoteEntity>
}
