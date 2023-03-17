package com.kingstek.companion.models.parish

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EvenImages (
    val imageTitle: String?,
    val eventImage: Int
) : Parcelable
