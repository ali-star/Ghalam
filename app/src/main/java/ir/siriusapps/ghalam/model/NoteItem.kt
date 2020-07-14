package ir.siriusapps.ghalam.model

import ir.siriusapps.ghalam.model.mapper.ItemMapper
import ir.siriusapps.ghalam.model.mapper.ItemModel
import ir.sitiusapps.ghalam.domain.model.Content
import ir.sitiusapps.ghalam.domain.model.Note
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class NoteItem(
) : ItemModel() {

    var id: Long? = null

    var title: String? = null

    var contentList: MutableList<ContentItem> = ArrayList()

    var color: Int? = null

    var pinned: Boolean = false

    var createDate: Date = Date()

    var updateDate: Date? = null

}

class NoteItemMapper @Inject constructor() : ItemMapper<Note, NoteItem> {

    override fun mapToPresentation(model: Note): NoteItem {
        val contents = ArrayList<ContentItem>()
        model.contentList.forEach {
            val content = ContentItem(it.contentType, it.index)
            content.id = it.id
            content.noteId = it.noteId
            contents.add(content)
        }
        return NoteItem().apply {
            id = model.id
            title = model.title
            contentList = contents
            color = model.color
            pinned = model.pinned
            createDate = model.createDate
            updateDate = model.updateDate
        }
    }

    override fun mapToDomain(itemModel: NoteItem): Note {
        val contents = ArrayList<Content>()
        itemModel.contentList.forEach {
            val content = Content(it.contentType, it.index)
            content.id = it.id
            content.noteId = it.noteId
            contents.add(content)
        }
        return Note(
            itemModel.id!!,
            itemModel.title,
            contents,
            itemModel.color,
            itemModel.pinned,
            itemModel.createDate,
            itemModel.updateDate
        )
    }


}