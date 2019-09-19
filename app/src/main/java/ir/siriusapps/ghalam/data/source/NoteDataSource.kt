package ir.siriusapps.ghalam.data.source

import io.reactivex.Single
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.NoteAndContents

interface NoteDataSource {

    fun saveNote(note: Note): Single<Long>

    fun getNote(noteLocalId: Long): Single<NoteAndContents>

    fun getAllNotes(): Single<List<NoteAndContents>>

}