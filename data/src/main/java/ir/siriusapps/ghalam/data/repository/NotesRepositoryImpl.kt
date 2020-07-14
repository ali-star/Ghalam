package ir.siriusapps.ghalam.data.repository

import androidx.paging.DataSource
import ir.siriusapps.ghalam.data.model.NoteAndContents
import ir.siriusapps.ghalam.data.model.NoteEntityMapper
import ir.siriusapps.ghalam.data.repository.source.local.GhalamDao
import ir.sitiusapps.ghalam.domain.model.Note
import ir.sitiusapps.ghalam.domain.repository.NotesRepository
import ir.sitiusapps.ghalam.domain.scope.ApplicationScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ApplicationScope
class NotesRepositoryImpl @Inject constructor(
    private val ghalamDao: GhalamDao,
    private val noteEntityMapper: NoteEntityMapper
) : NotesRepository {

    private val ioDispatcher = Dispatchers.IO

    override suspend fun saveNote(note: Note): Long = withContext(ioDispatcher) {
        return@withContext ghalamDao.saveNoteWithContents(noteEntityMapper.mapToData(note))
    }

    override suspend fun updateNote(note: Note) = withContext(ioDispatcher) {
        ghalamDao.saveNoteWithContents(noteEntityMapper.mapToData(note))
        return@withContext
    }

    override fun getNotes(): DataSource.Factory<Int, Note> {
        return ghalamDao.getNotesWithContents().map {
            noteEntityMapper.mapToDomain(it.getNoteWithContents())
        }
    }

    override suspend fun getNote(id: Long): Note = withContext(ioDispatcher) {
        val noteAndContents = ghalamDao.getNoteWithContents(id)
        return@withContext noteEntityMapper.mapToDomain(noteAndContents.getNoteWithContents())
    }

}