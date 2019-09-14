package ir.siriusapps.ghalam.data.source.local

import io.reactivex.Completable
import io.reactivex.Observable
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.source.DataSource

class LocalDataSource(private val ghalamDao: GhalamDao) : DataSource {

    override fun saveNote(note: Note): Completable {
        return ghalamDao.saveNote(note)
    }

    override fun getNote(id: String): Observable<Note> {
        return ghalamDao.getNote(id)
    }

    override fun getAllNotes(): Observable<List<Note>> {
        return ghalamDao.getAllNotes()
    }
}