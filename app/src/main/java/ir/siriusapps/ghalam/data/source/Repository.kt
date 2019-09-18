package ir.siriusapps.ghalam.data.source

import io.reactivex.Single
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.source.local.LocalNoteDataSource

class Repository (private val localDataSource: LocalNoteDataSource) : NoteDataSource {

    override fun saveNote(note: Note): Single<Long> {
        return localDataSource.saveNote(note)
    }

    override fun getNote(id: String): Single<Note> {
        return localDataSource.getNote(id)
    }

    override fun getAllNotes(): Single<List<Note>> {
        return localDataSource.getAllNotes()
    }

}