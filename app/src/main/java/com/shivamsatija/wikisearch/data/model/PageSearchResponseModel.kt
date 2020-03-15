package com.shivamsatija.wikisearch.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PageSearchResponseModel(
    @SerializedName("query")
    @Expose
    var query: Query?
) {
    data class Query(
        @SerializedName("pages")
        @Expose
        var pages: List<Page>?
    )
}