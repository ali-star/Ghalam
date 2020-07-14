package ir.sitiusapps.ghalam.domain.model

enum class ContentType(val value: Int) {
    TEXT(1), PHOTO(2), RECORDING(3), MUSIC(4);

    companion object {
        fun getByValue(value: Int): ContentType? {
            values().forEach {
                if (it.value == value)
                    return it
            }
            return null
        }
    }
}