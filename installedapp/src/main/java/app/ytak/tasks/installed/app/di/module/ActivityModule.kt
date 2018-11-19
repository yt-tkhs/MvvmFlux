package app.ytak.tasks.installed.app.di.module

import app.ytak.tasks.base.di.scope.PerActivityScope
import app.ytak.tasks.installed.app.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}