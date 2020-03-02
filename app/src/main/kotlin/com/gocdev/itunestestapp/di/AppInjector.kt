package com.gocdev.itunestestapp.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.gocdev.itunestestapp.ITunesApp
import dagger.android.AndroidInjection

/**
 * Object for injecting components with activity creation
 */
object AppInjector {
    fun init(iTunesApp: ITunesApp) {
        DaggerAppComponent.builder().application(iTunesApp)
            .build().inject(iTunesApp)
        iTunesApp
            .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) {

                }

                override fun onActivityResumed(activity: Activity) {

                }

                override fun onActivityPaused(activity: Activity) {

                }

                override fun onActivityStopped(activity: Activity) {

                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

                }

                override fun onActivityDestroyed(activity: Activity) {

                }
            })
    }

    private fun handleActivity(activity: Activity) {
        AndroidInjection.inject(activity)

    }
}