package ir.siriusapps.ghalam.di.module

import dagger.Module
import dagger.Provides
import ir.siriusapps.ghalam.data.repository.NotesRepositoryImpl
import ir.sitiusapps.ghalam.domain.repository.NotesRepository

@Module(includes = [GhalamDatabaseModule::class])
class RepositoryModule {

    @Provides
    fun notesAndContentRepository(
        notesAndContentsRepositoryImpl: NotesRepositoryImpl
    ): NotesRepository = notesAndContentsRepositoryImpl

}