package com.shivamsatija.wikisearch.ui.pagesearch

import com.shivamsatija.wikisearch.data.DataManager
import com.shivamsatija.wikisearch.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PageSearchPresenter<View : PageSearchMvpView> @Inject constructor(
    compositeDisposable: CompositeDisposable,
    var dataManager: DataManager
) : BasePresenter<View>(compositeDisposable), PageSearchMvpPresenter<View> {

    override fun fetchSearchPageResults(searchQuery: String) {
        if (getView() == null) return
        getView()!!.showLoading()

        compositeDisposable.add(
            dataManager.fetchPages(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.query?.pages?.run {
                        getView()!!.hideLoading()
                        getView()!!.showPageSearchResults(this)
                    }
                }, {
                    getView()!!.hideLoading()
                    getView()!!.showEmptyPageSearchResult()
                })
        )
    }

}