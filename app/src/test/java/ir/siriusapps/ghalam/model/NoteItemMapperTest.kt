package ir.siriusapps.ghalam.model

import android.graphics.Color
import ir.sitiusapps.ghalam.domain.model.*
import org.junit.Test
import org.junit.Assert.*
import java.util.*
import kotlin.collections.ArrayList

class NoteItemMapperTest {

    @Test
    fun mapToPresentation() {
        val noteItemMapper = NoteItemMapper()
        val note = createNoteModel()
        val noteItem = noteItemMapper.mapToPresentation(note)

        noteItem.contentList.indices.forEach {
            assertTrue(noteItem.contentList[it].id == note.contentList[it].id)
            assertTrue(noteItem.contentList[it].contentType == note.contentList[it].contentType)
            assertTrue(noteItem.contentList[it].index == note.contentList[it].index)
            assertTrue(noteItem.contentList[it].noteId == note.contentList[it].noteId)
        }
        assertTrue(noteItem.id == note.id)
        assertTrue(noteItem.title == note.title)
        assertTrue(noteItem.pinned == note.pinned)
        assertTrue(noteItem.color == note.color)
        assertTrue(noteItem.createDate == noteItem.createDate)
        assertTrue(noteItem.updateDate == noteItem.updateDate)
    }

    @Test
    fun mapToDomain() {
        val noteItemMapper = NoteItemMapper()
        val noteModel = createNoteItem()
        val note = noteItemMapper.mapToDomain(noteModel)

        note.contentList.indices.forEach {
            assertTrue(note.contentList[it].id == noteModel.contentList[it].id)
            assertTrue(note.contentList[it].contentType == noteModel.contentList[it].contentType)
            assertTrue(note.contentList[it].index == noteModel.contentList[it].index)
            assertTrue(note.contentList[it].noteId == noteModel.contentList[it].noteId)
        }
        assertTrue(note.id == noteModel.id)
        assertTrue(note.title == noteModel.title)
        assertTrue(note.pinned == noteModel.pinned)
        assertTrue(note.color == noteModel.color)
        assertTrue(note.createDate == note.createDate)
        assertTrue(note.updateDate == note.updateDate)
    }
}

fun createNoteModel(): Note {
    val contents = ArrayList<Content>()

    contents.add(createTextContent(1, 1, 0))
    contents.add(createTextContent(2, 1, 1))
    contents.add(createFileContent(0, 1, ContentType.PHOTO, 2))
    contents.add(createTextContent(3, 1, 3))

    return Note(
        0,
        "Im a title",
        contents,
        Color.WHITE,
        false,
        Date(),
        null
    )
}

fun createNoteItem(): NoteItem {
    val contents = ArrayList<ContentItem>()

    contents.add(createTextContentItem(1, 1, 0))
    contents.add(createTextContentItem(2, 1, 1))
    contents.add(createFileContentItem(0, 1, ContentType.PHOTO, 2))
    contents.add(createTextContentItem(3, 1, 3))

    return NoteItem().apply {
        id = 0
        title = "Im a title"
        contentList = contents
        color = Color.WHITE
        pinned = false
        createDate = Date()
        updateDate = null
    }
}

fun createFileContent(id: Long, noteId: Long, contentType: ContentType, index: Int): FileContent {
    val content = FileContent(contentType, index)
    content.id = id
    content.noteId = noteId
    content.filePath = ""
    content.fileSize = 256.0
    return content
}

fun createFileContentItem(id: Long, noteId: Long, contentType: ContentType, index: Int): FileContentItem {
    val content = FileContentItem(contentType, index)
    content.id = id
    content.noteId = noteId
    content.filePath = ""
    content.fileSize = 256.0
    return content
}

fun createTextContent(id: Long, noteId: Long, index: Int): TextContent {
    val content = TextContent(index)
    content.text = "Im a text"
    content.id = id
    content.noteId = noteId
    return content
}

fun createTextContentItem(id: Long, noteId: Long, index: Int): TextContentItem {
    val content = TextContentItem(index)
    content.text = "Im a text"
    content.id = id
    content.noteId = noteId
    return content
}