package ir.sitiusapps.ghalam.domain.repository

import androidx.paging.DataSource
import ir.sitiusapps.ghalam.domain.model.Note

interface NotesRepository {

    suspend fun saveNote(note: Note): Long

    suspend fun updateNote(note: Note)

    fun getNotes(): DataSource.Factory<Int, Note>

    suspend fun getNote(id: Long): Note

}