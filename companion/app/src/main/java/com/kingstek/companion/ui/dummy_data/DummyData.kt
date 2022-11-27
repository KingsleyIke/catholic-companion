package com.kingstek.companion.ui.dummy_data

import com.kingstek.companion.R
import com.kingstek.companion.ui.home.HomeModel
import com.kingstek.companion.ui.home.NewsModel

class DummyData {


    private val dataone: NewsModel = NewsModel("Something is happening", "Details goes here, breif of what it actually entails, breif of what it actually entails", R.drawable.rosary_bible)
    private val datatwo: NewsModel = NewsModel("Nothing is happening", "Details goes here, breif of what it actually entails, breif of what it actually entails", R.drawable.rosary_bible)
    private val datathree: NewsModel = NewsModel("What is happening", "Details goes here, breif of what it actually entails, breif of what it actually entails", R.drawable.rosary_bible)
    private val datafour: NewsModel = NewsModel("Who is happening", "Details goes here, breif of what it actually entails", R.drawable.rosary_bible)
    private val datafive: NewsModel = NewsModel("Something is happening","Details goes here, breif of what it actually entails",  R.drawable.rosary_bible)
    private val datasix: NewsModel = NewsModel("Nothing is happening", "Details goes here, breif of what it actually entails", R.drawable.rosary_bible)
    private val dataseven: NewsModel = NewsModel("What is happening", "Details goes here, breif of what it actually entails", R.drawable.rosary_bible)
    private val dataeight: NewsModel = NewsModel("Who is happening", "Details goes here, breif of what it actually entails", R.drawable.rosary_bible)
    private val datanine: NewsModel = NewsModel("Something is happening", "Details goes here, breif of what it actually entails", R.drawable.rosary_bible)
    private val dataten: NewsModel = NewsModel("Nothing is happening", "Details goes here, breif of what it actually entails", R.drawable.rosary_bible)
    private val dataeleven: NewsModel = NewsModel("What is happening", "Details goes here, breif of what it actually entails", R.drawable.rosary_bible)
    private val datatwelve: NewsModel = NewsModel("Who is happening", "Details goes here, breif of what it actually entails", R.drawable.rosary_bible)

    private val parish: HomeModel = HomeModel("Find Parish", "Looking for parish near you? Click here", R.drawable.ic_baseline_church_24)
    private val calendar = HomeModel("Calender ", "Get a daily list of saints, feasts and memorials", R.drawable.ic_baseline_calendar_24)
    private val catechism = HomeModel("Catechism", "Start and end your day with prayers", R.drawable.ic_baseline_catechism_2_24)
    private val parishList = HomeModel("List of Parish", "Get parish mass tie and activities", R.drawable.ic_baseline_church_24)
    private val dailyReadings = HomeModel("Daily Readings", "Coming Soon", R.drawable.ic_baseline_readings_24)
    private val simplePrayers = HomeModel("Simple Prayers", "Coming Soon", R.drawable.ic_baseline_record_voice_over_24)

    val homeList: ArrayList<HomeModel> = arrayListOf(parish, calendar, parishList, dailyReadings, catechism, simplePrayers)

    val newsList: ArrayList<NewsModel> = arrayListOf(dataone, datatwo, datathree, datafour, datafive, datasix, dataseven, dataeight, datanine, dataten, dataeleven, datatwelve)


}