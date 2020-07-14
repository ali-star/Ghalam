package ir.siriusapps.ghalam.model

import ir.siriusapps.ghalam.model.mapper.ItemMapper
import ir.sitiusapps.ghalam.domain.model.ContentType
import ir.sitiusapps.ghalam.domain.model.TextContent

class TextContentItem(index: Int) : ContentItem(ContentType.TEXT, index) {

    var text: String? = null

    constructor() : this(0)

}

class TextContentEntityMapper : ItemMapper<TextContent, TextContentItem> {

    override fun mapToPresentation(model: TextContent): TextContentItem {
        val entity = TextContentItem(model.index)
        entity.id = model.id
        entity.text = model.text
        return entity
    }

    override fun mapToDomain(itemModel: TextContentItem): TextContent {
        val model = TextContent(itemModel.index)
        model.id = itemModel.id
        model.text = itemModel.text
        return model
    }

}