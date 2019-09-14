package ir.siriusapps.ghalam.data.source

import io.reactivex.Completable
import io.reactivex.Observable
import ir.siriusapps.ghalam.data.Note

interface DataSource {

    fun saveNote(note: Note): Completable

    fun getNote(id: String): Observable<Note>

    fun getAllNotes(): Observable<List<Note>>

}