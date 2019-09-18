package ir.siriusapps.ghalam

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import ir.siriusapps.ghalam.data.Content
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.TextContent
import ir.siriusapps.ghalam.data.source.local.GhalamDatabase
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var database: GhalamDatabase

    @Before
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(appContext, GhalamDatabase::class.java).allowMainThreadQueries().build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndGetNotes() {
        val note = Note()
        note.title = "test title"

        database.ghalamDao().saveNote(note).test().assertValue {
            note.localId = it
            return@assertValue it != 0L
        }

        val contents: MutableList<Content> = ArrayList()

        val textContent = TextContent(note.localId, null, 0)
        textContent.text = "test text content"

        contents.add(textContent)

        note.contentList = contents

        database.ghalamDao().saveContents(contents).test().assertComplete()

        database.ghalamDao().saveNote(note).test().assertValue {
            val result = it
            return@assertValue it != 0L
        }

        database.ghalamDao().getAllNotes().test().assertValue {
            val result = it
            return@assertValue it.isNotEmpty()
        }

        database.ghalamDao().getContentsByNoteLocalId().test().assertValue {
            val result = it
            return@assertValue it.isNotEmpty()
        }

        database.ghalamDao().getNoteWithContent(note.localId).test().assertValue {
            val result = it
            return@assertValue it.note != null
        }
    }
}
