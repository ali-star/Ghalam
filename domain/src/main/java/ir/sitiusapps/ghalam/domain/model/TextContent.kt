package ir.sitiusapps.ghalam.domain.model

class TextContent(index: Int) : Content(ContentType.TEXT, index) {

    var text: String? = null

    constructor() : this(0)

}