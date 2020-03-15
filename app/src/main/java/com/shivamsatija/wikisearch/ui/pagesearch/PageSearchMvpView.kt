package com.shivamsatija.wikisearch.ui.pagesearch

import com.shivamsatija.wikisearch.data.model.Page
import com.shivamsatija.wikisearch.ui.base.MvpView

interface PageSearchMvpView : MvpView {

    fun showEmptyPageSearchResult()

    fun showPageSearchResults(results: List<Page>)
}