package com.kingstek.companion.models.parish

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageModel(
    val image: Uri,
    val title: String,
): Parcelable
