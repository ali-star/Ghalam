package ir.siriusapps.ghalam.data.source

import io.reactivex.Completable
import io.reactivex.Observable
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.source.local.LocalDataSource

class Repository (private val localDataSource: LocalDataSource) : DataSource {

    override fun saveNote(note: Note): Completable {
        return localDataSource.saveNote(note)
    }

    override fun getNote(id: String): Observable<Note> {
        return localDataSource.getNote(id)
    }

    override fun getAllNotes(): Observable<List<Note>> {
        return localDataSource.getAllNotes()
    }

}