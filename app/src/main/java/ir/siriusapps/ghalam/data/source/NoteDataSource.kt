package ir.siriusapps.ghalam.data.source

import io.reactivex.Single
import ir.siriusapps.ghalam.data.Note

interface NoteDataSource {

    fun saveNote(note: Note): Single<Long>

    fun getNote(noteLocalId: Long): Single<Note>

    fun getAllNotes(): Single<List<Note>>

}