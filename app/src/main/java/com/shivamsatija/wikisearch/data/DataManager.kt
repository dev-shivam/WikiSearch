package com.shivamsatija.wikisearch.data

import com.shivamsatija.wikisearch.data.model.PageSearchResponseModel
import com.shivamsatija.wikisearch.data.remote.ApiService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(
    private val service: ApiService
) {

    fun fetchPages(searchQuery: String) : Single<PageSearchResponseModel> {
        return service.fetchPages(searchQuery)
    }
}