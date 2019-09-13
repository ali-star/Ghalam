package ir.siriusapps.ghalam.data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Notes")
data class Note(
    val localId: String? = null,
    @SerializedName("id") val serverId: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("contentList") val contentList: List<Content>? = null,
    @SerializedName("color") val color: Int? = null,
    @SerializedName("pinned") val pinned: Boolean = false,
    @SerializedName("create_date") val createDate: Date? = null,
    @SerializedName("update_date") val updateDate: Date? = null)

open class Content(@SerializedName("contentType") val contentType: ContentType,
                   @SerializedName("index") val index: Int
) {
    var id: String? = null
}

class TextContent(index: Int) : Content(ContentType.TEXT, index) {
    @SerializedName("text") var text: String? = null
}

open class FileContent(contentType: ContentType, index: Int) : Content(contentType, index) {
    var filePath: String? = null
    var size: Double? = null
}

class PhotoContent(index: Int) : FileContent(ContentType.PHOTO, index)

class RecordingContent(index: Int) : FileContent(ContentType.RECORDING, index)

class MusicContent(index: Int) : FileContent(ContentType.MUSIC, index)

enum class ContentType {
    TEXT, PHOTO, RECORDING, MUSIC
}