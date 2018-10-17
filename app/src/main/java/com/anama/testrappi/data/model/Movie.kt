package com.anama.testrappi.data.model

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
class Movie: Item(), Parcelable {

    @PrimaryKey(autoGenerate = true)
    var idMovie: Long? = null
    @SerializedName("release_date")
    lateinit var releaseDate:String
    var adult:Boolean = false
    lateinit var title: String

    override fun getType(): Int = TYPE_MOVIE
}
