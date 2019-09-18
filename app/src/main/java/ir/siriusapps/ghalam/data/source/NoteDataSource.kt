package ir.siriusapps.ghalam.data.source

import io.reactivex.Single
import ir.siriusapps.ghalam.data.Note

interface NoteDataSource {

    fun saveNote(note: Note): Single<Long>

    fun getNote(id: String): Single<Note>

    fun getAllNotes(): Single<List<Note>>

}