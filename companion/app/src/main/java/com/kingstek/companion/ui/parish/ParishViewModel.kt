package com.kingstek.companion.ui.parish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kingstek.companion.ui.BaseViewModel

class ParishViewModel : BaseViewModel() {

    //todo only display parishes with aproved marked as yes
    //todo display parishes waiting for approval by admin
    private var _sortVisiblity = MutableLiveData<Boolean>(true)
    val sortVisiblity: LiveData<Boolean>
    get() = _sortVisiblity

    fun setSortVisiblity(value: Boolean) {
        _sortVisiblity.value = value
    }
}