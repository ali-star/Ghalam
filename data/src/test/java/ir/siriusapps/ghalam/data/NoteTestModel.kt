package ir.siriusapps.ghalam.data

import android.graphics.Color
import ir.siriusapps.ghalam.data.model.ContentEntity
import ir.siriusapps.ghalam.data.model.FileContentEntity
import ir.siriusapps.ghalam.data.model.NoteEntity
import ir.siriusapps.ghalam.data.model.TextContentEntity
import ir.sitiusapps.ghalam.domain.model.*
import java.util.*
import kotlin.collections.ArrayList

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

fun createNoteEntityModel(): NoteEntity {
    val contents = ArrayList<ContentEntity>()

    contents.add(createTextContentEntity(1, 1, 0))
    contents.add(createTextContentEntity(2, 1, 1))
    contents.add(createFileContentEntity(0, 1, ContentType.PHOTO, 2))
    contents.add(createTextContentEntity(3, 1, 3))

    return NoteEntity(
        0,
        "Im a title",
        contents,
        Color.WHITE,
        false,
        Date(),
        null
    )
}

fun createTextContent(id: Long, noteId: Long, index: Int): TextContent {
    val content = TextContent(index)
    content.text = "Im a text"
    content.id = id
    content.noteId = noteId
    return content
}

fun createTextContentEntity(id: Long, noteId: Long, index: Int): TextContentEntity {
    val content = TextContentEntity(index)
    content.text = "Im a text"
    content.id = id
    content.noteId = noteId
    return content
}

fun createFileContent(id: Long, noteId: Long, contentType: ContentType, index: Int): FileContent {
    val content = FileContent(contentType, index)
    content.id = id
    content.noteId = noteId
    content.filePath = ""
    content.fileSize = 256.0
    return content
}

fun createFileContentEntity(id: Long, noteId: Long, contentType: ContentType, index: Int): FileContentEntity {
    val content = FileContentEntity(contentType, index)
    content.id = id
    content.noteId = noteId
    content.filePath = ""
    content.fileSize = 256.0
    return content
}