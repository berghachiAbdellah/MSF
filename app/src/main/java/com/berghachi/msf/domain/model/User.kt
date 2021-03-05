package com.berghachi.msf.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val description: String,
    val hired: String,
    val id: String,
    val name: String,
    val picture: String
):Parcelable