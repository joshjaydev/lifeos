package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes WHERE user_id = :userId ORDER BY created_at DESC")
    fun getNotes(userId: String): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE notebook_id = :notebookId ORDER BY created_at DESC")
    fun getNotesByNotebook(notebookId: String): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNote(id: String): Flow<NoteEntity?>

    @Query("SELECT * FROM notes WHERE notebook_id = :notebookId AND is_journal = 1 AND journal_date = :date LIMIT 1")
    fun getJournalNote(notebookId: String, date: String): Flow<NoteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<NoteEntity>)

    @Update
    suspend fun update(note: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM notes WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
