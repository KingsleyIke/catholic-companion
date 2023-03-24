package com.kingstek.companion.models.diocese

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Diocese (
    val dioceseName: String,
    val dioceseList: List<String>
        ): Parcelable