package ir.siriusapps.ghalam.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ir.siriusapps.ghalam.di.scope.FragmentScope
import ir.siriusapps.ghalam.notes.NotesFragment
import ir.siriusapps.ghalam.notes.NotesViewModel

@Module
abstract class BindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun notesFragment(): NotesFragment

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    abstract fun bindViewModel(viewModel: NotesViewModel): ViewModel

}