package com.kingstek.companion.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.kingstek.companion.dummy_data.DummyData
import com.kingstek.companion.dummy_data.NewsModel
import com.kingstek.companion.dummy_data.ParishModel

//A base viewModel to load all como=mon codes across all viewModels
open class BaseViewModel : ViewModel() {

    //TODO handle check if user is loginned here, save user name and id in local db (phone)


    var mFirestore = FirebaseFirestore.getInstance()

    private val _email = MutableLiveData<String>()
    val email: MutableLiveData<String>
    get() = _email

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String>
    get() = _password

    val data = DummyData()

    private val _newsList = MutableLiveData<List<NewsModel>>().apply {
        value = data.newsList
    }
    val newsList = _newsList


    private val _parishList = MutableLiveData<List<ParishModel>>()
    val parishList: MutableLiveData<List<ParishModel>>
        get() = _parishList

}