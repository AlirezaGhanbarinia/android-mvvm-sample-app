package com.aliasadi.mvvm.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie : Parcelable {

    @Expose
    @SerializedName("description")
    var description: String? = null

    @Expose
    @SerializedName("image")
    var image: String? = null

    @Expose
    @SerializedName("title")
    var title: String? = null
}