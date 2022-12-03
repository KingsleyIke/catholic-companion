package com.kingstek.companion.ui.parish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kingstek.companion.dummy_data.HomeModel
import com.kingstek.companion.dummy_data.NewsModel
import com.kingstek.companion.dummy_data.ParishModel
import com.kingstek.companion.ui.BaseViewModel

class ParishViewModel : BaseViewModel() {

    private var _sortVisiblity = MutableLiveData<Boolean>(true)
    val sortVisiblity: LiveData<Boolean>
    get() = _sortVisiblity

    fun setSortVisiblity(value: Boolean) {
        _sortVisiblity.value = value
    }
}