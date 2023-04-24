package com.kingstek.companion.models.parish

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageModelUrl(
    val imageUri: String,
    val title: String,
): Parcelable
