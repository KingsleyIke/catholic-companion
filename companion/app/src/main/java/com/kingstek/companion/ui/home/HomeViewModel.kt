package com.kingstek.companion.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kingstek.companion.dummy_data.HomeModel
import com.kingstek.companion.ui.BaseViewModel
import com.kingstek.companion.utils.DataUtils

class HomeViewModel : BaseViewModel() {

    private val dataUtils = DataUtils()

    private val _text = MutableLiveData<String>().apply {
        value = "News"
    }
    val text: LiveData<String> = _text


    private val _homeList = MutableLiveData<List<HomeModel>>().apply {
        //todo use enum classes
        value = dataUtils.homeList
    }
    val homeList = _homeList
}