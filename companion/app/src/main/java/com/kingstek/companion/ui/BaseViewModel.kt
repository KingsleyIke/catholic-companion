package com.kingstek.companion.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kingstek.companion.dummy_data.DummyData
import com.kingstek.companion.dummy_data.NewsModel

//A base viewModel to load all como=mon codes across all viewModels
open class BaseViewModel : ViewModel() {

    val data = DummyData()

    private val _newsList = MutableLiveData<List<NewsModel>>().apply {
        value = data.newsList
    }
    val newsList = _newsList

}