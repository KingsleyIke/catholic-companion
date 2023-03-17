package com.kingstek.companion.dummy_data

import com.kingstek.companion.models.parish.*

data class ParishModel(
    val parishName: String?,
    val address: String?,
    val deanery: String?,
    val diocese: String?,
    val parishImage: List<Int>?,
    val sundayMas: List<String>?,
    val weekDayMass: List<WeekMasses>?,
    val pastoralTeam: List<PastoralTeam>?,
    val churchActivities: List<Activities>?,
    val churchAnnouncements: List<Announcement>?,
    val parishGalerry: List<ParishGalleryModel>?
    )
