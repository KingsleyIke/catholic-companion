package com.kingstek.companion.ui.parish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kingstek.companion.ui.BaseViewModel

class ParishViewModel : BaseViewModel() {

    private var _sortVisiblity = MutableLiveData<Boolean>(true)
    val sortVisiblity: LiveData<Boolean>
    get() = _sortVisiblity

    fun setSortVisiblity(value: Boolean) {
        _sortVisiblity.value = value
    }
}