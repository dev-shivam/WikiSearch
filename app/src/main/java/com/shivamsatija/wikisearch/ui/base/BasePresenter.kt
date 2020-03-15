package com.shivamsatija.wikisearch.ui.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<View: MvpView>(
    protected var compositeDisposable: CompositeDisposable
) : MvpPresenter<View> {

    private var _view: View? = null

    override fun attachView(view: View) {
        this._view = view
    }

    override fun detachView() {
        this._view = null
    }

    override fun getView(): View? = _view

    override fun detachPresenter() {
        compositeDisposable.dispose()
        this._view = null
    }

    override fun isViewAttached(): Boolean = _view != null

}