package com.shivamsatija.wikisearch.di.component

import android.content.Context
import com.shivamsatija.wikisearch.data.DataManager
import com.shivamsatija.wikisearch.di.ApplicationContext
import com.shivamsatija.wikisearch.di.module.ApplicationModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    @ApplicationContext
    fun applicationContext(): Context

    fun compositeDisposable(): CompositeDisposable

    fun dataManager(): DataManager
}