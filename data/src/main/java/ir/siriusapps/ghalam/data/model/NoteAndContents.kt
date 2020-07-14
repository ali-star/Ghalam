package ir.siriusapps.ghalam.data.model

import androidx.room.Embedded
import androidx.room.Relation
import ir.sitiusapps.ghalam.domain.model.Content
import ir.sitiusapps.ghalam.domain.model.FileContent
import ir.sitiusapps.ghalam.domain.model.TextContent

class NoteAndContents {

    @Embedded
    var note = NoteEntity()

    @Relation(
        parentColumn = "id",
        entityColumn = "note_id",
        entity = TextContentEntity::class
    )
    var textContents: MutableList<TextContentEntity> = ArrayList()

    @Relation(
        parentColumn = "id",
        entityColumn = "note_id",
        entity = FileContentEntity::class
    )
    var fileContents: MutableList<FileContentEntity> = ArrayList()

    private fun getContents(): MutableList<ContentEntity> {
        val contents: MutableList<ContentEntity> = ArrayList()
        contents.addAll(textContents)
        contents.addAll(fileContents)
        contents.sortByDescending { it.index }
        return contents
    }

    fun getNoteWithContents(): NoteEntity {
        val note = note
        note.contentList = getContents()
        return note
    }

}