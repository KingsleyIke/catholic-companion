package com.kingstek.companion.utils

import com.kingstek.companion.R
import com.kingstek.companion.dummy_data.HomeModel

class DataUtils {

    private val parish: HomeModel = HomeModel(
        "Find Parish",
        "Looking for parish near you? Click here",
        R.drawable.ic_baseline_church_24
    )
    private val calendar = HomeModel(
        "Calender ",
        "Get a daily list of saints, feasts and memorials",
        R.drawable.ic_baseline_calendar_24
    )
    private val catechism = HomeModel(
        "Catechism",
        "Start and end your day with prayers",
        R.drawable.ic_baseline_catechism_2_24
    )
    private val listOfParish = HomeModel(
        "List of Parish",
        "Get parish mass time and activities",
        R.drawable.ic_baseline_church_24
    )

    private val dailyReadings =
        HomeModel("Daily Readings", "Coming Soon", R.drawable.ic_baseline_readings_24)

    private val simplePrayers =
        HomeModel("Simple Prayers", "Coming Soon", R.drawable.ic_baseline_record_voice_over_24)


    val homeList: ArrayList<HomeModel> = arrayListOf(
        parish,
        calendar,
        listOfParish,
        dailyReadings,
        catechism,
        simplePrayers
    )
}