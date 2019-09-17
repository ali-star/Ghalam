package ir.siriusapps.ghalam.util

import androidx.room.TypeConverter
import ir.siriusapps.ghalam.data.ContentType
import java.util.*

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