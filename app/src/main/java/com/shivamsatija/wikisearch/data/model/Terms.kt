package com.shivamsatija.wikisearch.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Terms(
    @SerializedName("description")
    @Expose
    var description: List<String>?
)