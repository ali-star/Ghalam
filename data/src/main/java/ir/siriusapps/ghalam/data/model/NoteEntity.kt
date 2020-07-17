package ir.siriusapps.ghalam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ir.siriusapps.ghalam.data.base.EntityMapper
import ir.siriusapps.ghalam.data.base.EntityModel
import ir.sitiusapps.ghalam.domain.model.Content
import ir.sitiusapps.ghalam.domain.model.FileContent
import ir.sitiusapps.ghalam.domain.model.Note
import ir.sitiusapps.ghalam.domain.model.TextContent
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@Entity(tableName = "Notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long? = null,
    @SerializedName("title") @ColumnInfo(name = "title") var title: String? = null,
    @SerializedName("content_list") @Ignore var contentList: MutableList<ContentEntity> = ArrayList(),
    @SerializedName("color") @ColumnInfo(name = "color") var color: Int? = null,
    @SerializedName("pinned") @ColumnInfo(name = "pinned") var pinned: Boolean = false,
    @SerializedName("create_date") var createDate: Date = Date(),
    @SerializedName("update_date") var updateDate: Date? = null
) : EntityModel() {

    constructor() : this(0, null, ArrayList(), null, false, Date(), null)

}

class NoteEntityMapper @Inject constructor() : EntityMapper<Note, NoteEntity> {

    override fun mapToData(model: Note): NoteEntity {
        val contents = ArrayList<ContentEntity>()
        model.contentList.forEach {
            when (it) {
                is TextContent -> {
                    val content = TextContentEntity(it.index)
                    content.id = it.id
                    content.noteId = it.noteId
                    content.text = it.text
                    contents.add(content)
                }
                is FileContent -> {
                    val content = FileContentEntity(it.contentType, it.index)
                    content.id = it.id
                    content.noteId = it.noteId
                    content.filePath = it.filePath
                    content.fileSize = it.fileSize
                    contents.add(content)
                }
            }
        }
        return NoteEntity(
            model.id,
            model.title,
            contents,
            model.color,
            model.pinned,
            model.createDate,
            model.updateDate
        )
    }

    override fun mapToDomain(entityModel: NoteEntity): Note {
        val contents = ArrayList<Content>()
        entityModel.contentList.forEach {
            when (it) {
                is TextContentEntity -> {
                    val content = TextContent(it.index)
                    content.id = it.id
                    content.noteId = it.noteId
                    content.text = it.text
                    contents.add(content)
                }
                is FileContentEntity -> {
                    val content = FileContent(it.contentType, it.index)
                    content.id = it.id
                    content.noteId = it.noteId
                    content.filePath = it.filePath
                    content.fileSize = it.fileSize
                    contents.add(content)
                }
            }
        }
        return Note(
            entityModel.id,
            entityModel.title,
            contents,
            entityModel.color,
            entityModel.pinned,
            entityModel.createDate,
            entityModel.updateDate
        )
    }

}