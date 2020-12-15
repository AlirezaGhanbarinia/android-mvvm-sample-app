package com.aliasadi.mvvm.data.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movie : Parcelable {

    @Expose
    @SerializedName("description")
    var description: String?

    @Expose
    @SerializedName("image")
    var image: String?

    @Expose
    @SerializedName("title")
    var title: String?


    companion object {
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(parcel: Parcel): Movie? {
                return Movie(parcel)
            }

            override fun newArray(size: Int): Array<Movie?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(parcel: Parcel) {
        description = parcel.readString()
        image = parcel.readString()
        title = parcel.readString()
    }

    constructor(description: String?, image: String?, title: String?) {
        this.description = description
        this.image = image
        this.title = title
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(title)
    }
}