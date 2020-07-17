package ir.siriusapps.ghalam.data.repository

import android.app.Application
import android.content.Context
import android.graphics.Color
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import ir.siriusapps.ghalam.data.model.ContentEntity
import ir.siriusapps.ghalam.data.model.NoteEntity
import ir.siriusapps.ghalam.data.model.TextContentEntity
import ir.siriusapps.ghalam.data.repository.source.local.GhalamDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class NotesRepositoryImplTest {

    private lateinit var context: Context
    private lateinit var ghalamDatabase: GhalamDatabase

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Application>()
        ghalamDatabase = Room.databaseBuilder(context, GhalamDatabase::class.java, "ghalam.db")
            .allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        ghalamDatabase.close()
    }

    @Test
    fun saveNote() = runBlocking {
        val noteId = ghalamDatabase.ghalamDao().saveNote(createTextNoteEntity())
        assertTrue(noteId > 0)
    }

    @Test
    fun updateNote() {
    }

    @Test
    fun getNotes() {
    }

    @Test
    fun getNote() = runBlocking {
        val noteId = ghalamDatabase.ghalamDao().saveNote(createTextNoteEntity())
        assertTrue(noteId > 0)

        val savedNote = ghalamDatabase.ghalamDao().getNoteWithContents(noteId)
        assertTrue(savedNote.getNoteWithContents().title != null)
    }
}

fun createTextNoteEntity(): NoteEntity {
    val textContent = TextContentEntity(0)
    textContent.text = "test"
    return NoteEntity(
        null,
        "title",
        ArrayList<ContentEntity>().apply { add(textContent) },
        Color.WHITE,
        false,
        Date(),
        null
    )
}