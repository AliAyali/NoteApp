package com.example.note.domain.repository

import androidx.room.Query
import com.example.note.data.local.dataBase.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNoteByIds(noteId: List<Int>)
    suspend fun updateNote(note: NoteEntity)
    suspend fun getNoteById(id: Int): NoteEntity?
    fun getAllNotes(): Flow<List<NoteEntity>>

    fun getNotesOrderByTitle(): Flow<List<NoteEntity>>
    fun getNotesOrderByDateAsc(): Flow<List<NoteEntity>>
    fun getNotesOrderByDateDesc(): Flow<List<NoteEntity>>
}
