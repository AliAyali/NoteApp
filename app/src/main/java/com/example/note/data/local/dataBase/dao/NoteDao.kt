package com.example.note.data.local.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note.data.local.dataBase.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("DELETE FROM note_table WHERE id IN (:ids)")
    suspend fun deleteNotesByIds(ids: List<Int>)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE id = :noteId LIMIT 1")
    suspend fun getNoteById(noteId: Int): NoteEntity?

    @Query("SELECT * FROM note_table ORDER BY title ASC")
    fun getNotesOrderByTitle(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table ORDER BY date ASC")
    fun getNotesOrderByDateAsc(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table ORDER BY date DESC")
    fun getNotesOrderByDateDesc(): Flow<List<NoteEntity>>

}