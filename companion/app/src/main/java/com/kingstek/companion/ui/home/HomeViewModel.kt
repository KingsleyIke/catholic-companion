package com.kingstek.companion.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kingstek.companion.ui.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "News"
    }
    val text: LiveData<String> = _text


    private val _homeList = MutableLiveData<List<HomeModel>>().apply {
        value = data.homeList
    }
    val homeList = _homeList
}