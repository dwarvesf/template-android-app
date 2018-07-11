package com.dwarvesv.mvvm.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
        @SerializedName("id") var id: String,
        @SerializedName("name") var name: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }

        fun getListUserDummy(): ArrayList<User>? {
            val arrayList = ArrayList<User>()
            for (i in 1..20) {
                arrayList.add(User(id = "testId", name = "Test Data"))
            }
            return arrayList
        }
    }
}