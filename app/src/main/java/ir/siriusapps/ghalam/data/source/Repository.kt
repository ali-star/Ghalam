package ir.siriusapps.ghalam.data.source

import io.reactivex.Single
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.NoteAndContents
import ir.siriusapps.ghalam.data.source.local.LocalNoteDataSource

class Repository (private val localDataSource: LocalNoteDataSource) : NoteDataSource {

    override fun saveNote(note: Note): Single<Long> {
        return localDataSource.saveNote(note)
    }

    override fun getNote(noteLocalId: Long): Single<NoteAndContents> {
        return localDataSource.getNote(noteLocalId)
    }

    override fun getAllNotes(): Single<List<NoteAndContents>> {
        return localDataSource.getAllNotes()
    }

}