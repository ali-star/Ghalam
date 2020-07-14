package ir.siriusapps.ghalam.data.repository.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.siriusapps.ghalam.data.model.*
import ir.siriusapps.ghalam.data.repository.source.local.typeConverter.RoomContentTypeConverter
import ir.siriusapps.ghalam.data.repository.source.local.typeConverter.RoomDateConverter
import ir.sitiusapps.ghalam.domain.model.*

@Database(
    entities = [
        NoteEntity::class, ContentEntity::class, TextContentEntity::class, FileContentEntity::class,
        LabelEntity::class
    ],
    version = 5, exportSchema = false
)
@TypeConverters(RoomDateConverter::class, RoomContentTypeConverter::class)
abstract class GhalamDatabase : RoomDatabase() {

    abstract fun ghalamDao(): GhalamDao

}