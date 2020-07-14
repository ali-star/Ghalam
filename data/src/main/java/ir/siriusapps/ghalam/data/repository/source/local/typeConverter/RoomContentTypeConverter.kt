package ir.siriusapps.ghalam.data.repository.source.local.typeConverter

import androidx.room.TypeConverter
import ir.sitiusapps.ghalam.domain.model.ContentType

class RoomContentTypeConverter {

    @TypeConverter
    fun toContentType(value: Int?): ContentType? {
        return if (value == null) null else ContentType.getByValue(value)
    }

    @TypeConverter
    fun toInt(contentType: ContentType): Int {
        return contentType.value
    }

}