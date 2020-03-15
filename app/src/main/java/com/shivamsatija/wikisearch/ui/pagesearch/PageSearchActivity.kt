package com.shivamsatija.wikisearch.ui.pagesearch

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivamsatija.wikisearch.R
import com.shivamsatija.wikisearch.data.model.Page
import com.shivamsatija.wikisearch.di.component.ActivityComponent
import com.shivamsatija.wikisearch.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_page_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class PageSearchActivity
    : BaseActivity(), PageSearchMvpView {

    @Inject
    lateinit var presenter: PageSearchMvpPresenter<PageSearchMvpView>

    private var adapter: PagesAdapter? = null

    private var disposable: Disposable? = null

    private val searchPublishSubject: PublishSubject<String>
            = PublishSubject.create()

    override fun getLayoutResource(): Int = R.layout.activity_page_search

    override fun performDependencyInjection(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.attachView(this)
    }

    override fun setup(state: Bundle?) {

        setupToolbar()
        setupRecyclerView()
        setupTextWatcher()

        disposable = searchPublishSubject.subscribeOn(AndroidSchedulers.mainThread())
            .debounce(250, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (TextUtils.isEmpty(it)) {
                    adapter?.clearData()
                } else {
                    presenter.fetchSearchPageResults(it)
                }
            }, {
                it.message?.let { message ->
                    Log.e("PAGE_SEARCH", message)
                }
            })
    }

    override fun showEmptyPageSearchResult() {
    }

    override fun showPageSearchResults(results: List<Page>) {
        adapter?.clearData()
        adapter?.addData(results)
    }

    override fun showLoading() {
        super.showLoading()
//        if (!swipeRefreshLayout.isRefreshing) {
//            swipeRefreshLayout.isRefreshing = true
//        }
    }

    override fun hideLoading() {
        super.hideLoading()
//        if (swipeRefreshLayout.isRefreshing) {
//            swipeRefreshLayout.isRefreshing = false
//            swipeRefreshLayout.isEnabled = false
//        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setupRecyclerView() {
        adapter = PagesAdapter()
        adapter?.addItemInteractionListener(object : PagesAdapter.ItemInteractionListener {
            override fun onItemClick(url: String) {
                CustomTabsIntent.Builder()
                    .build()
                    .launchUrl(this@PageSearchActivity, Uri.parse(url))
            }
        })

        rvSearchResults.apply {
            layoutManager = GridLayoutManager(this@PageSearchActivity, 2)
            adapter = this@PageSearchActivity.adapter
        }
    }

    private fun setupTextWatcher() {
        etSearchQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.toString()?.let { searchQuery ->
                    searchPublishSubject.onNext(searchQuery)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}
