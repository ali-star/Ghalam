package ir.siriusapps.ghalam.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.siriusapps.ghalam.data.Content
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.TextContent
import ir.siriusapps.ghalam.util.RoomContentTypeConverter
import ir.siriusapps.ghalam.util.RoomDateConverter

@Database(entities = [Note::class, Content::class, TextContent::class], version = 1, exportSchema = false)
@TypeConverters(RoomDateConverter::class, RoomContentTypeConverter::class)
abstract class GhalamDatabase : RoomDatabase() {

    abstract fun ghalamDao(): GhalamDao

}