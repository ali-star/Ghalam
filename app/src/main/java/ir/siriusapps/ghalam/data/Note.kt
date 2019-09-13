package ir.siriusapps.ghalam.data

import androidx.room.*
import com.google.gson.annotations.SerializedName
import ir.siriusapps.ghalam.util.RoomDateConverter
import java.util.*

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true) var localId: Int?,
    @SerializedName("id") var serverId: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("contentList") @Ignore var contentList: List<Content>? = null,
    @SerializedName("color") var color: Int? = null,
    @SerializedName("pinned") var pinned: Boolean? = false,
    @SerializedName("create_date") var createDate: Date? = null,
    @SerializedName("update_date") var updateDate: Date? = null) {

    constructor() : this(null, null, null, null, null, null, null, null)

}

open class Content(@SerializedName("contentType") val contentType: ContentType,
                   @SerializedName("index") val index: Int
) {
    var id: String? = null
}

@Entity(tableName = "textContents")
class TextContent(index: Int) : Content(ContentType.TEXT, index) {
    @SerializedName("text") var text: String? = null
}

@Entity(tableName = "FileContents")
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