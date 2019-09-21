package ir.siriusapps.ghalam

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import ir.siriusapps.ghalam.data.*
import ir.siriusapps.ghalam.data.source.Repository
import ir.siriusapps.ghalam.data.source.local.GhalamDatabase
import ir.siriusapps.ghalam.data.source.local.LocalNoteDataSource
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

        val contents: MutableList<Content> = ArrayList()

        val textContent = TextContent(0)
        textContent.text = "test text content"

        val fileContent = FileContent(ContentType.PHOTO, 1)
        fileContent.filePath = "test file path"

        contents.add(textContent)
        contents.add(fileContent)

        note.contentList = contents

        val repository = Repository(LocalNoteDataSource(database.ghalamDao()))

        repository.saveNote(note).test().assertValue {
            note.localId = it
            return@assertValue it > 0L
        }

        database.ghalamDao().getTextContentByNoteId(note.localId).test().assertValue {
            return@assertValue it.isNotEmpty()
        }

        repository.getNote(note.localId).test().assertValue {
            return@assertValue it.contentList.isNotEmpty()
        }
    }
}
