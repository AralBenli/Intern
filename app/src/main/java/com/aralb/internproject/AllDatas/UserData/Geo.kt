package com.aralb.internproject.AllDatas.UserData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geo(
    val lat: String,
    val lng: String
) : Parcelable