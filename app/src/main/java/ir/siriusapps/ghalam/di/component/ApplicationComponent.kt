package ir.siriusapps.ghalam.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ir.siriusapps.ghalam.GhalamApp
import ir.siriusapps.ghalam.di.module.GsonModule
import ir.siriusapps.ghalam.di.scope.ApplicationScope

@ApplicationScope
@Component(modules = [AndroidInjectionModule::class, GsonModule::class])
interface ApplicationComponent : AndroidInjector<GhalamApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ghalamApp: GhalamApp): ApplicationComponent
    }

}