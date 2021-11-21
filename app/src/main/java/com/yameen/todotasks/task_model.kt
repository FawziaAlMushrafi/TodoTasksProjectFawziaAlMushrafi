package com.yameen.todotasks

import android.os.Parcel
import android.os.Parcelable

data class task_model(

    val _id:Int = 0,
    val date:String? = "",
    val title:String? = "",
    val task :String? = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(_id)
        parcel.writeString(date)
        parcel.writeString(title)
        parcel.writeString(task)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<task_model> {
        override fun createFromParcel(parcel: Parcel): task_model {
            return task_model(parcel)
        }

        override fun newArray(size: Int): Array<task_model?> {
            return arrayOfNulls(size)
        }
    }
}
