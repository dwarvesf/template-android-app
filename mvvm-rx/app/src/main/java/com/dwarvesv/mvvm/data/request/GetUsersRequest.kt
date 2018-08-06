package com.dwarvesv.mvvm.data.request

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GetUsersRequest(
        @SerializedName("id") var id: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GetUsersRequest> {
        override fun createFromParcel(parcel: Parcel): GetUsersRequest {
            return GetUsersRequest(parcel)
        }

        override fun newArray(size: Int): Array<GetUsersRequest?> {
            return arrayOfNulls(size)
        }
    }
}