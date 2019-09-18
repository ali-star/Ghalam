package ir.siriusapps.ghalam.data

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "local_id") var localId: Long,
    @SerializedName("id") @ColumnInfo(name = "id") var serverId: String? = null,
    @SerializedName("title") @ColumnInfo(name = "title") var title: String? = null,
    @SerializedName("content_list") @Ignore var contentList: List<Content>? = null,
    @SerializedName("color") @ColumnInfo(name = "color") var color: Int? = null,
    @SerializedName("pinned") @ColumnInfo(name = "pinned") var pinned: Boolean? = false,
    @SerializedName("create_date") var createDate: Date? = null,
    @SerializedName("update_date") var updateDate: Date? = null) {

    constructor() : this(0, null, null, null, null, null, null, null)

}

@Entity(tableName = "contents")
open class Content(
    @ColumnInfo(name = "note_local_id") var noteLocalId: Long,
    @SerializedName("note_id") @ColumnInfo(name = "note_id") var noteId: String?,
    @SerializedName("content_type") @ColumnInfo(name = "content_type") var contentType: ContentType?,
    @SerializedName("index") @ColumnInfo(name = "index") var index: Int?
) {
    var id: String? = null
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "local_content_id") var localContentId: Int? = null

}

@Entity(tableName = "textContents")
class TextContent(noteLocalId: Long, noteId: String?, index: Int) : Content(noteLocalId, noteId, ContentType.TEXT, index) {
    @SerializedName("text") var text: String? = null

    constructor(): this(0, null, 0)
}

@Entity(tableName = "FileContents")
open class FileContent(noteLocalId: Long, noteId: String?, contentType: ContentType?, index: Int) : Content(noteLocalId, noteId, contentType, index) {

    var filePath: String? = null
    var size: Double? = null

    constructor(): this(0, "", null, 0)
}

class PhotoContent(noteLocalId: Long, noteId: String?, index: Int) : FileContent(noteLocalId, noteId, ContentType.PHOTO, index)

class RecordingContent(noteLocalId: Long, noteId: String?, index: Int) : FileContent(noteLocalId, noteId, ContentType.RECORDING, index)

class MusicContent(noteLocalId: Long, noteId: String?, index: Int) : FileContent(noteLocalId, noteId, ContentType.MUSIC, index)

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
    var note: Note? = null

    @Relation(parentColumn = "local_id", entityColumn = "note_local_id", entity = TextContent::class)
    var contents: List<TextContent>? = null

}