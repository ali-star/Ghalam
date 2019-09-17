package ir.siriusapps.ghalam.data

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "local_id") var localId: Int,
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
    @SerializedName("note_id") @ColumnInfo(name = "note_id") val noteId: String,
    @SerializedName("content_type") @ColumnInfo(name = "content_type") val contentType: ContentType?,
    @SerializedName("index") @ColumnInfo(name = "index") val index: Int?
) {
    var id: String? = null
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "local_content_id") var localContentId: Int? = null

}

@Entity(tableName = "textContents",
    foreignKeys = [
        ForeignKey(
            entity = Note::class,
            parentColumns = ["note_id"],
            childColumns = ["note_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
            )
    ])
class TextContent(noteId: String, index: Int) : Content(noteId, ContentType.TEXT, index) {
    @SerializedName("text") var text: String? = null

    constructor(): this("", 0)
}

@Entity(tableName = "FileContents")
open class FileContent(noteId: String, contentType: ContentType?, index: Int) : Content(noteId, contentType, index) {

    var filePath: String? = null
    var size: Double? = null

    constructor(): this("", null, 0)
}

class PhotoContent(noteId: String, index: Int) : FileContent(noteId, ContentType.PHOTO, index)

class RecordingContent(noteId: String, index: Int) : FileContent(noteId, ContentType.RECORDING, index)

class MusicContent(noteId: String, index: Int) : FileContent(noteId, ContentType.MUSIC, index)

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