package ir.siriusapps.ghalam.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ir.siriusapps.ghalam.GhalamApp
import ir.siriusapps.ghalam.di.module.BindingModule
import ir.siriusapps.ghalam.di.module.ContextModule
import ir.siriusapps.ghalam.di.module.GsonModule
import ir.siriusapps.ghalam.di.module.RepositoryModule
import ir.sitiusapps.ghalam.domain.scope.ApplicationScope

@ApplicationScope
@Component(modules = [
    ContextModule::class, RepositoryModule::class, GsonModule::class,
    BindingModule::class, AndroidInjectionModule::class
])
interface ApplicationComponent : AndroidInjector<GhalamApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ghalamApp: GhalamApp): ApplicationComponent
    }

}