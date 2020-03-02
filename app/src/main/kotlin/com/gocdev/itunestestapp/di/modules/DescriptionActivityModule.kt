package com.gocdev.itunestestapp.di.modules

import com.gocdev.itunestestapp.ui.description.DescriptionActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class DescriptionActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeDescription(): DescriptionActivity
}