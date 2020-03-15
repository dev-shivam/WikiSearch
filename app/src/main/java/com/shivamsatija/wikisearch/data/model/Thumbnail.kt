package com.shivamsatija.wikisearch.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("source")
    @Expose
    var source: String
)