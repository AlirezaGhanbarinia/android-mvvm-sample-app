package com.aliasadi.mvvm.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(

            @Expose
            @SerializedName("description") var description: String,

            @Expose
            @SerializedName("image") var image: String,

            @Expose
            @SerializedName("title") var title: String

            ) : Parcelable