package com.camp.campingapp.model

import android.os.Parcel
import android.os.Parcelable


data class HostData(
    var uid: Int? = null,
    var hno: Int? = null,
    var hid: String? = null,
    var addr: String? = null,
    var campname: String? = null,
    var hostname: String? = null,
    var tel: String? = null,
    var content: String? = null,
    var type: String? = "host"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(uid)
        parcel.writeValue(hno)
        parcel.writeString(hid)
        parcel.writeString(addr)
        parcel.writeString(campname)
        parcel.writeString(hostname)
        parcel.writeString(tel)
        parcel.writeString(content)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HostData> {
        override fun createFromParcel(parcel: Parcel): HostData {
            return HostData(parcel)
        }

        override fun newArray(size: Int): Array<HostData?> {
            return arrayOfNulls(size)
        }
    }
}