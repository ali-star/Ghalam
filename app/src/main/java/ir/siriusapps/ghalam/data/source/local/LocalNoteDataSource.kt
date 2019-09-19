package ir.siriusapps.ghalam.data.source.local

import io.reactivex.Single
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.NoteAndContents
import ir.siriusapps.ghalam.data.source.NoteDataSource

class LocalNoteDataSource(private val ghalamDao: GhalamDao) : NoteDataSource {

    override fun saveNote(note: Note): Single<Long> {
        return Single.fromCallable {
            return@fromCallable ghalamDao.saveNoteWithContents(note)
        }
    }

    override fun getNote(noteLocalId: Long): Single<NoteAndContents> {
        return ghalamDao.getNoteWithContents(noteLocalId)
    }

    override fun getAllNotes(): Single<List<NoteAndContents>> {
        return ghalamDao.getNoteAllWithContents()
    }
}