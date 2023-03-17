package com.kingstek.companion.models.parish

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Parish(
    val id: String,
    val adminApproval: String,
    val name: String?,
    val address: String?,
    val website : String?,
    val deanery: String?,
    val diocese: String?,
    val parishImage: List<Int>?,
    val sundayMass: List<String>?,
    val weekDayMass: List<WeekMasses>?,
    val pastoralTeam: List<PastoralTeam>?,
    val churchActivities: List<Activities>?,
    val churchAnnouncements: List<Announcement>?,
    val parishGallery: List<ParishGalleryModel>?,
    val approved: Int?,
    val lastupdate: UpdateDetails?
) : Parcelable
