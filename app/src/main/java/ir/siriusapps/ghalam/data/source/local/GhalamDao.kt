package ir.siriusapps.ghalam.data.source.local

import androidx.room.*
import io.reactivex.Single
import ir.siriusapps.ghalam.data.*

@Dao
interface GhalamDao {

    @Transaction
    fun saveNoteWithContents(note: Note): Long {
        val noteLocalId = saveNote(note)

        deleteTextContentsByNoteId(noteLocalId)
        deleteFileContentsByNoteId(noteLocalId)

        val textContents: MutableList<TextContent> = ArrayList()
        val fileContents: MutableList<FileContent> = ArrayList()

        note.contentList.forEach {
            it.noteLocalId = noteLocalId
            if (it is TextContent)
                textContents.add(it)
            else if (it is FileContent)
                fileContents.add(it)
        }

        if (textContents.isNotEmpty())
            saveTextContents(textContents)

        if (fileContents.isNotEmpty())
            saveFileContents(fileContents)

        return noteLocalId
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note: Note): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTextContents(textContents: List<TextContent>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFileContents(fileContents: List<FileContent>)

    @Query("DELETE FROM fileContents WHERE note_local_id = :noteLocalId")
    fun deleteFileContentsByNoteId(noteLocalId: Long)

    @Query("SELECT * FROM textContents WHERE note_local_id = :noteLocalId")
    fun getTextContentByNoteId(noteLocalId: Long): Single<List<TextContent>>

    @Query("DELETE FROM textContents WHERE note_local_id = :noteLocalId")
    fun deleteTextContentsByNoteId(noteLocalId: Long)

    @Query("SELECT * FROM Notes WHERE local_id = :localId")
    fun getNoteWithContents(localId: Long): Single<NoteAndContents>

    @Query("SELECT * FROM Notes")
    fun getNoteAllWithContents(): Single<List<NoteAndContents>>

}