package ir.siriusapps.ghalam.data.repository.source.local.typeConverter

import androidx.room.TypeConverter
import java.util.*

class RoomDateConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

}