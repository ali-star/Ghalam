package ir.siriusapps.ghalam.data

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "local_id") var localId: Long,
    @SerializedName("id") @ColumnInfo(name = "id") var serverId: String? = null,
    @SerializedName("title") @ColumnInfo(name = "title") var title: String? = null,
    @SerializedName("content_list") @Ignore var contentList: MutableList<Content> = ArrayList(),
    @SerializedName("color") @ColumnInfo(name = "color") var color: Int? = null,
    @SerializedName("pinned") @ColumnInfo(name = "pinned") var pinned: Boolean? = false,
    @SerializedName("create_date") var createDate: Date? = null,
    @SerializedName("update_date") var updateDate: Date? = null) {

    constructor() : this(0, null, null, ArrayList(), null, null, null, null)

}

@Entity(tableName = "contents")
open class Content(
    @SerializedName("content_type") @ColumnInfo(name = "content_type") var contentType: ContentType?,
    @SerializedName("index") @ColumnInfo(name = "index") var index: Int?
) {
    var id: String? = null
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "local_content_id") var localContentId: Int? = null
    @ColumnInfo(name = "note_local_id") var noteLocalId: Long? = null
    @SerializedName("note_id") @ColumnInfo(name = "note_id") var noteId: String? = null
}

@Entity(tableName = "textContents")
class TextContent(index: Int) : Content(ContentType.TEXT, index) {
    @SerializedName("text") var text: String? = null

    constructor(): this(0)
}

@Entity(tableName = "fileContents")
open class FileContent(contentType: ContentType?, index: Int) : Content(contentType, index) {

    var filePath: String? = null
    var size: Double? = null

    constructor(): this(null, 0)
}

enum class ContentType(val value: Int) {
    TEXT(1), PHOTO(2), RECORDING(3), MUSIC(4);

    companion object {
        fun getByValue(value: Int): ContentType? {
            values().forEach {
                if (it.value == value)
                    return it
            }
            return null
        }
    }
}

class NoteAndContents {

    @Embedded
    var note = Note()

    @Relation(parentColumn = "local_id", entityColumn = "note_local_id", entity = TextContent::class)
    var textContents: MutableList<TextContent> = ArrayList()

    @Relation(parentColumn = "local_id", entityColumn = "note_local_id", entity = FileContent::class)
    var fileContents: MutableList<FileContent> = ArrayList()

    private fun getContents(): MutableList<Content> {
        val contents: MutableList<Content> = ArrayList()
        contents.addAll(textContents)
        contents.addAll(fileContents)
        contents.sortByDescending { it.index }
        return contents
    }

    fun getNoteWithContents(): Note {
        val note = note
        note.contentList = getContents()
        return note
    }

}