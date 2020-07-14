package ir.sitiusapps.ghalam.domain.model

open class Content(
    var contentType: ContentType?,
    var index: Int = 0
): Model() {

    var id: Long? = 0
    var noteId: Long? = null

}