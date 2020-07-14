package ir.sitiusapps.ghalam.domain.model

open class FileContent(contentType: ContentType?, index: Int) : Content(contentType, index) {

    var filePath: String? = null
    var fileSize: Double? = null

    constructor() : this(null, 0)
}