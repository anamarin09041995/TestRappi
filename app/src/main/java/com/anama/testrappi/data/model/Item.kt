package com.anama.testrappi.data.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
open class Item {

    var id: Long = 0
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0
    var popularity: Double = 0.0
    lateinit var overview: String
    var category: Int = 0

    open fun getType():Int = 0

    companion object {
        @JvmStatic
        val TYPE_SERIE: Int = 0
        @JvmStatic
        val TYPE_MOVIE: Int = 1

        val CATEGORY_TOP_RATED = 0
        val CATEGORY_POPULAR = 1
        val CATEGORY_UPCOMING = 2
    }
}