package ir.sitiusapps.ghalam.domain.model

import java.util.*
import kotlin.collections.ArrayList

data class Note(
    var id: Long? = null,
    var title: String? = null,
    var contentList: MutableList<Content> = ArrayList(),
    var color: Int? = null,
    var pinned: Boolean = false,
    var createDate: Date = Date(),
    var updateDate: Date? = null
) : Model()