package ir.siriusapps.ghalam.data.source.local

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import ir.siriusapps.ghalam.data.Label
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.NoteAndContents
import ir.siriusapps.ghalam.data.source.LabelDataSource
import ir.siriusapps.ghalam.data.source.NoteDataSource

class LocalNoteDataSource(private val ghalamDao: GhalamDao) :
    NoteDataSource, LabelDataSource
{
    // region label
    override fun saveLabel(label: Label): Completable = ghalamDao.saveLable(label)

    override fun getLabels(): Single<List<Label>> = ghalamDao.getLabels()

    override fun deleteLabel(localId: Long): Completable = ghalamDao.deleteLabel(localId)
    // endregion

    // region Note
    override fun saveNote(note: Note): Single<Long> {
        return Single.fromCallable {
            return@fromCallable ghalamDao.saveNoteWithContents(note)
        }
    }

    override fun getNote(noteLocalId: Long): Single<Note> {
        return Single.fromCallable {
            val noteAndContents = ghalamDao.getNoteWithContents(noteLocalId).blockingGet()
            return@fromCallable noteAndContents.getNoteWithContents()
        }
    }

    override fun getAllNotes(): Single<List<Note>> {
        return Single.fromCallable {
            val notes: MutableList<Note> = ArrayList()
            ghalamDao.getNoteAllWithContents().blockingGet().forEach {
                notes.add(it.getNoteWithContents())
            }
            return@fromCallable notes
        }
    }
    // endregion
}