package ir.siriusapps.ghalam.model

import ir.siriusapps.ghalam.model.mapper.ItemMapper
import ir.sitiusapps.ghalam.domain.model.ContentType
import ir.sitiusapps.ghalam.domain.model.FileContent

open class FileContentItem(contentType: ContentType?, index: Int) : ContentItem(contentType, index) {

    var filePath: String? = null

    var fileSize: Double? = null

}

class FileContentItemMapper : ItemMapper<FileContent, FileContentItem> {

    override fun mapToPresentation(model: FileContent): FileContentItem {
        val entity = FileContentItem(
            model.contentType,
            model.index
        )
        entity.id = model.id
        entity.filePath = model.filePath
        entity.fileSize = model.fileSize
        return entity
    }

    override fun mapToDomain(itemModel: FileContentItem): FileContent {
        val model = FileContent(
            itemModel.contentType,
            itemModel.index
        )
        itemModel.id = itemModel.id
        itemModel.filePath = itemModel.filePath
        itemModel.fileSize = itemModel.fileSize
        return model
    }

}