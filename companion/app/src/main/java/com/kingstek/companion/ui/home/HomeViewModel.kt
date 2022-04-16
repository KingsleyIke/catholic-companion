package com.kingstek.companion.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kingstek.companion.dummy_data.DummyData

class HomeViewModel : ViewModel() {

    private val data = DummyData()
    lateinit var newsModel: List<NewsModel>

    private val _text = MutableLiveData<String>().apply {
        value = "News"
    }
    val text: LiveData<String> = _text

    private val _newsList = MutableLiveData<List<NewsModel>>().apply {
        value = data.newsList
    }
    val newsList = _newsList

    private val _homeList = MutableLiveData<List<HomeModel>>().apply {
        value = data.homeList
    }
    val homeList = _homeList
}