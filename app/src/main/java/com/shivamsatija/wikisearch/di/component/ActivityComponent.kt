package com.shivamsatija.wikisearch.di.component

import com.shivamsatija.wikisearch.di.PerActivity
import com.shivamsatija.wikisearch.di.module.ActivityModule
import com.shivamsatija.wikisearch.ui.pagesearch.PageSearchActivity
import dagger.Component

@PerActivity
@Component(
    modules = [ActivityModule::class],
    dependencies = [ApplicationComponent::class]
)
interface ActivityComponent {

    fun inject(pageSearchActivity: PageSearchActivity)

}