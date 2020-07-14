package ir.siriusapps.ghalam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.siriusapps.ghalam.data.base.EntityModel
import ir.sitiusapps.ghalam.domain.model.ContentType

@Entity
open class ContentEntity(
    @ColumnInfo(name = "content_type") var contentType: ContentType?,
    @ColumnInfo(name = "index") var index: Int
) : EntityModel() {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    var id: Long? = null

    @ColumnInfo(name = "note_id")
    var noteId: Long? = null

}