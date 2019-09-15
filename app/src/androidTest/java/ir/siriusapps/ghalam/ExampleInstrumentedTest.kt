package ir.siriusapps.ghalam

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import ir.siriusapps.ghalam.data.Note
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
        database.ghalamDao().saveNote(note).test().assertComplete()

        database.ghalamDao().getAllNotes().test().assertValue {
            return@assertValue it.get(0).title.equals(note.title)
        }
    }
}
