package com.kingstek.companion.models.parish

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Activities(
    val title: String?,
    val dayAndTime: String?,
    ) : Parcelable
