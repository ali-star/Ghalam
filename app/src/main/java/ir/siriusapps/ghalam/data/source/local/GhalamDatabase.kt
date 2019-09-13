package ir.siriusapps.ghalam.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.util.RoomDateConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(RoomDateConverter::class)
abstract class GhalamDatabase : RoomDatabase() {

    abstract fun ghalamDao(): GhalamDao

}