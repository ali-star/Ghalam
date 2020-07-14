package ir.siriusapps.ghalam.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ir.sitiusapps.ghalam.domain.scope.FragmentScope
import ir.siriusapps.ghalam.ui.note.NoteFragment
import ir.siriusapps.ghalam.ui.note.NoteViewModel
import ir.siriusapps.ghalam.ui.notes.NotesFragment
import ir.siriusapps.ghalam.ui.notes.NotesViewModel

@Module
abstract class BindingModule {

    // region Notes fragment
    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun notesFragment(): NotesFragment

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun bindNotesSharedViewModel(viewModel: NoteViewModel): ViewModel
    // endregion

    // region Note fragment
    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun noteFragment(): NoteFragment

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    abstract fun bindNoteViewModel(viewModel: NotesViewModel): ViewModel
    // endregion

}