package com.kingstek.companion.models.parish

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PastoralTeam(
    val postion: String?,
    val title: String?,
    val name: String?,
    val phoneNumber: String?
    ) : Parcelable
