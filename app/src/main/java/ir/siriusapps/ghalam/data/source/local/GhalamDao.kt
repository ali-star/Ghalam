package ir.siriusapps.ghalam.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import ir.siriusapps.ghalam.data.Note

@Dao
interface GhalamDao {

    @Insert
    fun saveNote(note: Note): Completable

    @Query("SELECT * FROM Notes WHERE local_id = :id")
    fun getNote(id: String): Observable<Note>

    @Query("SELECT * FROM Notes")
    fun getAllNotes(): Observable<List<Note>>

}