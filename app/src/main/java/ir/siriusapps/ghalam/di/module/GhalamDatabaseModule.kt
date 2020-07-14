package ir.siriusapps.ghalam.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ir.siriusapps.ghalam.data.repository.source.local.GhalamDao
import ir.siriusapps.ghalam.data.repository.source.local.GhalamDatabase

@Module
class GhalamDatabaseModule {

    @Provides
    fun ghalamDatabase(context: Context): GhalamDao {
        val ghalamDatabase = Room.databaseBuilder(context, GhalamDatabase::class.java, "ghalam.db")
            .fallbackToDestructiveMigration()
            .build()
        return ghalamDatabase.ghalamDao()
    }

}