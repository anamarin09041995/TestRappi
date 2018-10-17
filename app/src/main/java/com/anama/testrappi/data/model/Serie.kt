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
class Serie: Item(), Parcelable {

    @PrimaryKey(autoGenerate = true)
    var idSerie:Long? = null

    @SerializedName("first_air_date")
    lateinit var firstAirDate:String
    lateinit var name: String

    override fun getType(): Int = TYPE_SERIE
}