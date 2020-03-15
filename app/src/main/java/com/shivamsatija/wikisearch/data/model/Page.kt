package com.shivamsatija.wikisearch.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Page(
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail?,
    @SerializedName("terms")
    @Expose
    var terms: Terms?,
    @SerializedName("visitingwatchers")
    @Expose
    var visitingwatchers: Int = 0,
    @SerializedName("fullurl")
    @Expose
    var fullurl: String?
)
