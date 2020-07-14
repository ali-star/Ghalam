package ir.siriusapps.ghalam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import ir.siriusapps.ghalam.data.base.EntityMapper
import ir.sitiusapps.ghalam.domain.model.ContentType
import ir.sitiusapps.ghalam.domain.model.TextContent

@Entity(tableName = "textContents")
class TextContentEntity(index: Int) : ContentEntity(ContentType.TEXT, index) {

    @ColumnInfo(name = "text") var text: String? = null

    constructor() : this(0)

}

class TextContentEntityMapper : EntityMapper<TextContent, TextContentEntity> {

    override fun mapToData(model: TextContent): TextContentEntity {
        val entity = TextContentEntity(model.index)
        entity.id = model.id
        entity.text = model.text
        return entity
    }

    override fun mapToDomain(entityModel: TextContentEntity): TextContent {
        val model = TextContent(entityModel.index)
        model.id = entityModel.id
        model.text = entityModel.text
        return model
    }

}