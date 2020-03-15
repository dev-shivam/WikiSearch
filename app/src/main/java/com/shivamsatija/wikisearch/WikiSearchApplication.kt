package com.shivamsatija.wikisearch

import android.app.Application
import com.shivamsatija.wikisearch.di.component.ApplicationComponent
import com.shivamsatija.wikisearch.di.component.DaggerApplicationComponent
import com.shivamsatija.wikisearch.di.module.ApplicationModule

class WikiSearchApplication : Application() {

    private lateinit var _applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        _applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent() = _applicationComponent

}