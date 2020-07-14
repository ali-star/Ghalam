package ir.siriusapps.ghalam.data.repository.source.local

import androidx.paging.DataSource
import androidx.room.*
import ir.siriusapps.ghalam.data.model.*

@Dao
interface GhalamDao {

    // region Label
    @Insert
    suspend fun saveLabel(label: LabelEntity)

    @Query("SELECT * FROM labels")
    suspend fun getLabels(): List<LabelEntity>

    @Query("DELETE FROM Labels WHERE id = :id")
    suspend fun deleteLabel(id: Long)
    // endregion

    // region Note
    @Transaction
    suspend fun saveNoteWithContents(note: NoteEntity): Long {
        val noteLocalId = saveNote(note)

        deleteTextContentsByNoteId(noteLocalId)
        deleteFileContentsByNoteId(noteLocalId)

        val textContents: MutableList<TextContentEntity> = ArrayList()
        val fileContents: MutableList<FileContentEntity> = ArrayList()

        note.contentList.forEach {
            it.noteId = noteLocalId
            if (it is TextContentEntity)
                textContents.add(it)
            else if (it is FileContentEntity)
                fileContents.add(it)
        }

        if (textContents.isNotEmpty())
            saveTextContents(textContents)

        if (fileContents.isNotEmpty())
            saveFileContents(fileContents)

        return noteLocalId
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTextContents(textContents: List<TextContentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFileContents(fileContents: List<FileContentEntity>)

    @Query("DELETE FROM fileContents WHERE note_id = :noteId")
    suspend fun deleteFileContentsByNoteId(noteId: Long)

    @Query("SELECT * FROM textContents WHERE note_id = :noteId")
    suspend fun getTextContentByNoteId(noteId: Long): List<TextContentEntity>

    @Query("DELETE FROM textContents WHERE note_id = :noteId")
    suspend fun deleteTextContentsByNoteId(noteId: Long)

    @Query("SELECT * FROM Notes WHERE id = :id")
    suspend fun getNoteWithContents(id: Long): NoteAndContents

    @Query("SELECT * FROM Notes ORDER BY createDate DESC")
    fun getNotesWithContents(): DataSource.Factory<Int, NoteAndContents>
    // endregion

}