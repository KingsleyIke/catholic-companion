package com.kingstek.companion.models.parish

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ParishGalleryModel(
    val eventTitle: String?,
    val date: String?,
    val eventDescription: String?,
    val eventDetails: String?,
    val eventImages: List<EvenImages>?,
    val expiringDate: Date?
) : Parcelable
