package ir.siriusapps.ghalam.data.source.local

import io.reactivex.Single
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.source.NoteDataSource

class LocalNoteDataSource(private val ghalamDao: GhalamDao) : NoteDataSource {

    override fun saveNote(note: Note): Single<Long> {
        return ghalamDao.saveNote(note)
    }

    override fun getNote(id: String): Single<Note> {
        return ghalamDao.getNote(id)
    }

    override fun getAllNotes(): Single<List<Note>> {
        return ghalamDao.getAllNotes()
    }
}