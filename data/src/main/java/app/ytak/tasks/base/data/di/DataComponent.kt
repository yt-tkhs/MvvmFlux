package app.ytak.tasks.base.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DaoModule::class,
        DatabaseModule::class,
        DataModule::class
    ]
)
interface DataComponent : DataComponentModules {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): DataComponent
    }
}