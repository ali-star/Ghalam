package ir.siriusapps.ghalam

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ir.siriusapps.ghalam.di.component.DaggerApplicationComponent

class GhalamApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out GhalamApp> {
        return DaggerApplicationComponent.factory().create(this)
    }

}