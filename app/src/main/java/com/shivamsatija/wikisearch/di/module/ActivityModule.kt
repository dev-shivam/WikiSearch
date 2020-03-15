package com.shivamsatija.wikisearch.di.module

import android.app.Activity
import android.content.Context
import com.shivamsatija.wikisearch.di.ActivityContext
import com.shivamsatija.wikisearch.ui.pagesearch.PageSearchMvpPresenter
import com.shivamsatija.wikisearch.ui.pagesearch.PageSearchMvpView
import com.shivamsatija.wikisearch.ui.pagesearch.PageSearchPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val activity: Activity
) {

    @Provides
    @ActivityContext
    fun provideActivityContext(): Context = activity

    @Provides
    fun providePageSearchPresenter(
        presenter: PageSearchPresenter<PageSearchMvpView>
    ): PageSearchMvpPresenter<PageSearchMvpView> {
        return presenter
    }
}