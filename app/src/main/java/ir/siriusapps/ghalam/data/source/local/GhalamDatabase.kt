package ir.siriusapps.ghalam.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.siriusapps.ghalam.data.Note

@Database(entities = [Note::class], version = 1)
abstract class GhalamDatabase : RoomDatabase() {

    abstract fun ghalamDao(): GhalamDao

}