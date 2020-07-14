package ir.siriusapps.ghalam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import ir.siriusapps.ghalam.data.base.EntityMapper
import ir.sitiusapps.ghalam.domain.model.ContentType
import ir.sitiusapps.ghalam.domain.model.FileContent

@Entity(tableName = "fileContents")
open class FileContentEntity(contentType: ContentType?, index: Int) : ContentEntity(contentType, index) {

    @ColumnInfo(name = "file_path") var filePath: String? = null

    @ColumnInfo(name = "file_size") var fileSize: Double? = null

}

class FileContentEntityMapper : EntityMapper<FileContent, FileContentEntity> {

    override fun mapToData(model: FileContent): FileContentEntity {
        val entity = FileContentEntity(
            model.contentType,
            model.index
        )
        entity.id = model.id
        entity.filePath = model.filePath
        entity.fileSize = model.fileSize
        return entity
    }

    override fun mapToDomain(entityModel: FileContentEntity): FileContent {
        val model = FileContent(
            entityModel.contentType,
            entityModel.index
        )
        entityModel.id = entityModel.id
        entityModel.filePath = entityModel.filePath
        entityModel.fileSize = entityModel.fileSize
        return model
    }

}