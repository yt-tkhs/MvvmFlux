package app.ytak.tasks.instant.app.di.module

import app.ytak.tasks.base.di.scope.PerActivityScope
import app.ytak.tasks.instant.app.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @PerActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeMainActivity(): MainActivity
}