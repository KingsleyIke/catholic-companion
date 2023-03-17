package com.kingstek.companion.models.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class news(
    val id: String,
    val headline: String?,
    val detail: String?,
    val headlineImage: Int?
) : Parcelable
