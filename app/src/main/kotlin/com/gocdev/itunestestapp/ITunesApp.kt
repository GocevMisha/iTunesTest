package com.gocdev.itunestestapp

import android.app.Application
import com.gocdev.itunestestapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class ITunesApp : Application(), HasAndroidInjector {
    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any>? = androidInjector

}