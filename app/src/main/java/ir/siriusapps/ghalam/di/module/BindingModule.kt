package ir.siriusapps.ghalam.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ir.siriusapps.ghalam.di.scope.FragmentScope
import ir.siriusapps.ghalam.note.NoteFragment
import ir.siriusapps.ghalam.note.NoteViewModel
import ir.siriusapps.ghalam.notes.NotesFragment
import ir.siriusapps.ghalam.NotesSharedViewModel

@Module
abstract class BindingModule {

    // region Notes fragment
    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun notesFragment(): NotesFragment

    @Binds
    @IntoMap
    @ViewModelKey(NotesSharedViewModel::class)
    abstract fun bindNotesSharedViewModel(viewModel: NotesSharedViewModel): ViewModel
    // endregion

    // region Note fragment
    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun noteFragment(): NoteFragment

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun bindNoteViewModel(viewModel: NoteViewModel): ViewModel
    // endregion

}