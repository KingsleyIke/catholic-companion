package com.kingstek.companion.dummy_data

import java.util.*

data class ParishGalleryModel(
    val eventTitle: String?,
    val date: String?,
    val eventDescription: String?,
    val eventDetails: String?,
    val eventImages: List<EvenImages>?,
    val expiringDate: Date?
)
