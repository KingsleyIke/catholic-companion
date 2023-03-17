package com.kingstek.companion.models.parish

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeekMasses (
    val day: String?,
    val time: List<String>?
        ) : Parcelable
