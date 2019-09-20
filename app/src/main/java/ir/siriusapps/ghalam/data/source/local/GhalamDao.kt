package ir.siriusapps.ghalam.data.source.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ir.siriusapps.ghalam.data.*

@Dao
interface GhalamDao {

    @Transaction
    fun saveNoteWithContents(note: Note): Long {
        val noteId = saveNote(note)

        val textContents: MutableList<TextContent> = ArrayList()
        val fileContents: MutableList<FileContent> = ArrayList()

        note.contentList?.forEach {
            it.noteLocalId = noteId
            if (it is TextContent)
                textContents.add(it)
            else if (it is FileContent)
                fileContents.add(it)
        }

        if (textContents.isNotEmpty())
            saveTextContents(textContents)

        if (fileContents.isNotEmpty())
            saveFileContents(fileContents)

        return noteId
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note: Note): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTextContents(textContents: List<TextContent>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFileContents(fileContents: List<FileContent>)

    @Query("SELECT * FROM textContents WHERE note_local_id = :localId")
    fun getTextContentByNoteId(localId: Long): Single<List<TextContent>>

    @Query("SELECT * FROM Notes WHERE local_id = :localId")
    fun getNoteWithContents(localId: Long): Single<NoteAndContents>

    @Query("SELECT * FROM Notes")
    fun getNoteAllWithContents(): Single<List<NoteAndContents>>

}