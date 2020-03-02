package com.gocdev.itunestestapp.di

import android.app.Application
import com.gocdev.itunestestapp.ITunesApp
import com.gocdev.itunestestapp.di.modules.AppModule
import com.gocdev.itunestestapp.di.modules.DescriptionActivityModule
import com.gocdev.itunestestapp.di.modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        DescriptionActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(iTunesApp: ITunesApp)
}