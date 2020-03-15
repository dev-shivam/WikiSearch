package com.shivamsatija.wikisearch.data.remote

import com.shivamsatija.wikisearch.data.model.PageSearchResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    val url = "/w/api.php?action=query" +
//            "&format=json" +
//            "&prop=pageimages%7Cpageterms%7Cinfo" +
//            "&generator=prefixsearch" +
//            "&formatversion=2" +
//            "&piprop=thumbnail" +
//            "&pithumbsize=50" +
//            "&wbptterms=description" +
//            "&inprop=url" +
//            "&gpssearch=Albert%20Ei" +
//            "&gpslimit=20"

    @GET("/w/api.php?action=query" +
            "&format=json" +
            "&prop=pageimages%7Cpageterms%7Cinfo" +
            "&generator=prefixsearch" +
            "&formatversion=2" +
            "&piprop=thumbnail" +
            "&pithumbsize=500" +
            "&pilimit=10" +
            "&inprop=url" +
            "&wbptterms=description" +
            "&gpslimit=20")
    fun fetchPages(@Query("gpssearch") searchQuery: String): Single<PageSearchResponseModel>
}