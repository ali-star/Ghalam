package ir.siriusapps.ghalam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.siriusapps.ghalam.data.base.EntityMapper
import ir.siriusapps.ghalam.data.base.EntityModel
import ir.sitiusapps.ghalam.domain.model.Label

@Entity(tableName = "Labels")
data class LabelEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long,
    @ColumnInfo(name = "note_Id") var noteId: Long? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "color") var color: Int? = null
) : EntityModel() {

    constructor() : this(0, 0, null, null)

}

class LabelEntityMapper : EntityMapper<Label, LabelEntity> {

    override fun mapToData(model: Label): LabelEntity {
        return LabelEntity(
            model.id,
            model.noteId,
            model.name,
            model.color
        )
    }

    override fun mapToDomain(entityModel: LabelEntity): Label {
        return Label(
            entityModel.id,
            entityModel.noteId,
            entityModel.name,
            entityModel.color
        )
    }

}