package ir.siriusapps.ghalam.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import ir.siriusapps.ghalam.GhalamApp

@Module
abstract class ContextModule {

    @Binds
    abstract fun context(ghalamApp: GhalamApp): Context

}