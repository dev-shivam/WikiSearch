package com.shivamsatija.wikisearch.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.shivamsatija.wikisearch.data.model.Page
import com.shivamsatija.wikisearch.data.model.PageSearchResponseModel
import com.shivamsatija.wikisearch.data.remote.ApiService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class DataManagerTest {

    // Subject under test
    lateinit var dataManager: DataManager

    // Dependencies of subject under test
    var apiService: ApiService = mock()

    var mockResponse: PageSearchResponseModel = mock()

    @Before
    fun setUp() {
        dataManager = DataManager(apiService)
    }

    @Test
    fun shouldFetchPagesAndComplete() {
        whenever(apiService.fetchPages("Albert"))
            .doReturn(Single.just(mockResponse))

        val singleObserver = dataManager.fetchPages("Albert")

        singleObserver.test().assertComplete()
    }
}