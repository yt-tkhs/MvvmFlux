package app.ytak.tasks.base.data.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}