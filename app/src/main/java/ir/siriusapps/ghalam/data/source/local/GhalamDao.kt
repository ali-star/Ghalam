package ir.siriusapps.ghalam.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ir.siriusapps.ghalam.data.Content
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.NoteAndContents

@Dao
interface GhalamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note: Note): Single<Long>

    @Query("SELECT * FROM Notes WHERE local_id = :id")
    fun getNote(id: String): Single<Note>

    @Query("SELECT * FROM Notes")
    fun getAllNotes(): Single<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveContents(textContents: List<Content>): Completable

    @Query("SELECT * FROM textContents")
    fun getContentsByNoteLocalId(): Single<List<Content>>

    @Query("SELECT * FROM Notes WHERE local_id = :localId")
    fun getNoteWithContent(localId: Long): Single<NoteAndContents>

}