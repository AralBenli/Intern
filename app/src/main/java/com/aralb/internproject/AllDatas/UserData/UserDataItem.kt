package com.aralb.internproject.AllDatas.UserData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDataItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) : Parcelable