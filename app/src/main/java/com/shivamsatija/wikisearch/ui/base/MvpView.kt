package com.shivamsatija.wikisearch.ui.base

interface MvpView {

    fun showToast(message: String?)

    fun showLoading()

    fun hideLoading()
}