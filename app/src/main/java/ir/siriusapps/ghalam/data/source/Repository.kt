package ir.siriusapps.ghalam.data.source

import io.reactivex.Completable
import io.reactivex.Single
import ir.siriusapps.ghalam.data.Label
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.NoteAndContents
import ir.siriusapps.ghalam.data.source.local.LocalNoteDataSource

class Repository (private val localDataSource: LocalNoteDataSource) :
    NoteDataSource, LabelDataSource
{
    // region Label
    override fun saveLabel(label: Label): Completable = localDataSource.saveLabel(label)

    override fun getLabels(): Single<List<Label>> = localDataSource.getLabels()

    override fun deleteLabel(localId: Long): Completable = localDataSource.deleteLabel(localId)
    // endregion

    // region Note
    override fun saveNote(note: Note): Single<Long> {
        return localDataSource.saveNote(note)
    }

    override fun getNote(noteLocalId: Long): Single<Note> {
        return localDataSource.getNote(noteLocalId)
    }

    override fun getAllNotes(): Single<List<Note>> {
        return localDataSource.getAllNotes()
    }
    // endregion

}