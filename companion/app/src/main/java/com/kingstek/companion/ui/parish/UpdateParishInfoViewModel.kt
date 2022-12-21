package com.kingstek.companion.ui.parish

import androidx.lifecycle.MutableLiveData
import com.kingstek.companion.dummy_data.DummyData
import com.kingstek.companion.ui.BaseViewModel

class UpdateParishInfoViewModel : BaseViewModel() {

        private val _parishListSpinner = MutableLiveData<ArrayList<String>>().apply {
        value = DummyData().parishListSpinner
    }
    val parishListSpinner = _parishListSpinner
}