package com.kingstek.companion.models.parish

import android.os.Parcelable
import com.kingstek.companion.models.user.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateDetails (
    val uploader: User?,
    val approvedBy: User,
        ) : Parcelable
