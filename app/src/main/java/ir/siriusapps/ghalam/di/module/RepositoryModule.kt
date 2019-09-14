package ir.siriusapps.ghalam.di.module

import dagger.Module
import dagger.Provides
import ir.siriusapps.ghalam.data.source.Repository
import ir.siriusapps.ghalam.data.source.local.GhalamDao
import ir.siriusapps.ghalam.data.source.local.LocalDataSource

@Module(includes = [GhalamDatabaseModule::class])
class RepositoryModule {

    @Provides
    fun repository(ghalamDao: GhalamDao): Repository {
        return Repository(LocalDataSource(ghalamDao))
    }

}