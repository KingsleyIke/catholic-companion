package com.kingstek.companion.models.parish

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Announcement (
    val title: String?,
    val details: String?,
    val reoccuring: Boolean?,
    val expiringDate: Date?
    ) : Parcelable
