package com.example.note.data.local.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note.data.local.dataBase.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("DELETE FROM note_table WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Int): Int

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM note_table WHERE id = :noteId LIMIT 1")
    suspend fun getNoteById(noteId: Int): NoteEntity?

}