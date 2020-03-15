package com.shivamsatija.wikisearch.ui.base

interface MvpPresenter<View : MvpView> {

    fun attachView(view: View)

    fun detachView()

    fun getView(): View?

    fun detachPresenter()

    fun isViewAttached(): Boolean
}