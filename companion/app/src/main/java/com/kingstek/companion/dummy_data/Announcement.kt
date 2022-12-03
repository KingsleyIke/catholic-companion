package com.kingstek.companion.dummy_data

import java.util.*

data class Announcement (
    val title: String?,
    val details: String?,
    val reoccuring: Boolean?,
    val expiringDate: Date?
    )
