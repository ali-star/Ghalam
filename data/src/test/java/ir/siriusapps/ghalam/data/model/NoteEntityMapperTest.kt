package ir.siriusapps.ghalam.data.model

import ir.siriusapps.ghalam.data.createNoteEntityModel
import ir.siriusapps.ghalam.data.createNoteModel
import org.junit.Test
import org.junit.Assert.*

class NoteEntityMapperTest {

    @Test
    fun mapToData() {
        val note = createNoteModel()

        val noteEntityMapper = NoteEntityMapper()

        val noteEntity = noteEntityMapper.mapToData(note)

        noteEntity.contentList.indices.forEach {
            assertTrue(noteEntity.contentList[it].id == note.contentList[it].id)
            assertTrue(noteEntity.contentList[it].contentType == note.contentList[it].contentType)
            assertTrue(noteEntity.contentList[it].index == note.contentList[it].index)
            assertTrue(noteEntity.contentList[it].noteId == note.contentList[it].noteId)
        }
        assertTrue(noteEntity.id == note.id)
        assertTrue(noteEntity.title == note.title)
        assertTrue(noteEntity.pinned == note.pinned)
        assertTrue(noteEntity.color == note.color)
        assertTrue(noteEntity.createDate == noteEntity.createDate)
        assertTrue(noteEntity.updateDate == noteEntity.updateDate)
    }

    @Test
    fun mapToDomain() {
        val noteEntity = createNoteEntityModel()

        val noteEntityMapper = NoteEntityMapper()

        val note = noteEntityMapper.mapToDomain(noteEntity)

        note.contentList.indices.forEach {
            assertTrue(note.contentList[it].id == noteEntity.contentList[it].id)
            assertTrue(note.contentList[it].contentType == noteEntity.contentList[it].contentType)
            assertTrue(note.contentList[it].index == noteEntity.contentList[it].index)
            assertTrue(note.contentList[it].noteId == noteEntity.contentList[it].noteId)
        }
        assertTrue(note.id == noteEntity.id)
        assertTrue(note.title == noteEntity.title)
        assertTrue(note.pinned == noteEntity.pinned)
        assertTrue(note.color == noteEntity.color)
        assertTrue(note.createDate == note.createDate)
        assertTrue(note.updateDate == note.updateDate)
    }
}