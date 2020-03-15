package com.shivamsatija.wikisearch.ui.pagesearch

import com.shivamsatija.wikisearch.ui.base.MvpPresenter

interface PageSearchMvpPresenter<View : PageSearchMvpView>
    : MvpPresenter<View> {

    fun fetchSearchPageResults(searchQuery: String)
}