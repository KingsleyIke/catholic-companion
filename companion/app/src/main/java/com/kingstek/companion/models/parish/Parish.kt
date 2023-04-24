package com.kingstek.companion.models.parish

import android.os.Parcelable
import com.kingstek.companion.models.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class Parish(
    val createdBy: String? = null,
    val approvedBy: User? = null,
    var parishName: String? = null,
    var address: String? = null,
    val website: String? = null,
    val deanery: String? = null,
    var diocese: String? = null,
    var parishImage: List<ImageModelUrl>? = null,
    var sundayMass: List<String>? = null,
    var weekDayMass: List<WeekMasses>? =null,
    var pastoralTeam: List<PastoralTeam>? = null,
    val churchActivities: List<Activities>? = null,
    val churchAnnouncements: List<Announcement>? = null,
    val parishGallery: List<ParishGalleryModel>? = null,
    val approved: Int? = null,
    val lastUpdate: UpdateDetails? = null
) : Parcelable
