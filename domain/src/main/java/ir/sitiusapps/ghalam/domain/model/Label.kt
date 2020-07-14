package ir.sitiusapps.ghalam.domain.model

data class Label(
    var id: Long,
    var noteId: Long? = null,
    var name: String? = null,
    var color: Int? = null
) : Model()