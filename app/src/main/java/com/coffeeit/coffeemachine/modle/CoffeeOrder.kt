package com.coffeeit.coffeemachine.modle

import android.os.Parcel
import android.os.Parcelable

/**
 * Represent all setting that made by user
 */
class CoffeeOrder(val machineId: String) : Parcelable {
    var typeId: String = ""
    var typeName: String = ""
    var sizeId: String = ""
    var sizeName: String = ""
    var extras: List<CoffeeExtraInfo> = ArrayList()

    constructor(parcel: Parcel) : this(parcel.readString() ?: "") {
        typeId = parcel.readString() ?: ""
        typeName = parcel.readString() ?: ""
        sizeId = parcel.readString() ?: ""
        sizeName = parcel.readString() ?: ""
        extras = parcel.createTypedArrayList(CoffeeExtraInfo) ?: arrayListOf()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(machineId)
        parcel.writeString(typeId)
        parcel.writeString(typeName)
        parcel.writeString(sizeId)
        parcel.writeString(sizeName)
        parcel.writeTypedList(extras)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoffeeOrder> {
        override fun createFromParcel(parcel: Parcel): CoffeeOrder {
            return CoffeeOrder(parcel)
        }

        override fun newArray(size: Int): Array<CoffeeOrder?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "$typeName + $sizeName + $extras \\n"
    }
}

class CoffeeExtraInfo(
    var extraId: String,
    var extraName: String,
    var subId: String,
    var subName: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(extraId)
        parcel.writeString(extraName)
        parcel.writeString(subId)
        parcel.writeString(subName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoffeeExtraInfo> {
        override fun createFromParcel(parcel: Parcel): CoffeeExtraInfo {
            return CoffeeExtraInfo(parcel)
        }

        override fun newArray(size: Int): Array<CoffeeExtraInfo?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "[$extraName + $subName]"
    }
}