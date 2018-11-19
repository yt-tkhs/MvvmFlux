package app.ytak.tasks.base.data.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DaoModule::class,
        DatabaseModule::class
    ]
)
interface DataComponent : DataComponentModules {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): DataComponent
    }
}