package com.kingstek.companion.dummy_data

import com.kingstek.companion.R
import com.kingstek.companion.ui.home.HomeModel
import com.kingstek.companion.ui.home.NewsModel

class DummyData {


    private val dataone: NewsModel = NewsModel("Something is happening", R.drawable.rosary_bible)
    private val datatwo: NewsModel = NewsModel("Nothing is happening", R.drawable.rosary_bible)
    private val datathree: NewsModel = NewsModel("What is happening", R.drawable.rosary_bible)
    private val datafour: NewsModel = NewsModel("Who is happening", R.drawable.rosary_bible)

    private val parish: HomeModel = HomeModel("Find Parishes", "Looking for parish near you? Click here", R.drawable.rosary_bible)
    private val simplePrayers = HomeModel("Simple Prayers", "Start and end your day with prayers", R.drawable.rosary_bible)
    private val calendar = HomeModel("Calendar", "Get a daily list of saints, feasts and memorials", R.drawable.rosary_bible)
    private val catechism = HomeModel("Catechism", "Know your faith", R.drawable.rosary_bible)
//    private val blank = HomeModel("Blank", "Know your faith", R.drawable.rosary_bible)
//    private val empty = HomeModel("Empty", "Know your faith", R.drawable.rosary_bible)

    val homeList: ArrayList<HomeModel> = arrayListOf(parish, simplePrayers, calendar, catechism)

    val newsList: ArrayList<NewsModel> = arrayListOf(dataone, datatwo, datathree, datafour)


}