package ir.siriusapps.ghalam.model

import ir.siriusapps.ghalam.model.mapper.ItemModel
import ir.sitiusapps.ghalam.domain.model.ContentType

open class ContentItem(
    var contentType: ContentType?,
    var index: Int
) : ItemModel() {

    var id: Long? = null

    var noteId: Long? = null

}