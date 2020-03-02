package com.gocdev.itunestestapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gocdev.itunestestapp.di.ViewModelKey
import com.gocdev.itunestestapp.ui.description.DescriptionActivityViewModel
import com.gocdev.itunestestapp.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DescriptionActivityViewModel::class)
    abstract fun bindDescriptionActivityViewModel(descriptionActivityViewModel: DescriptionActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ITunesViewModelFactory): ViewModelProvider.Factory
}