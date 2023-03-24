package com.kingstek.companion.ui.parish.updates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kingstek.companion.dummy_data.DummyData
import com.kingstek.companion.models.parish.PastoralTeam
import com.kingstek.companion.ui.BaseViewModel

class UpdateParishInfoViewModel : BaseViewModel() {

        private val _parishListSpinner = MutableLiveData<ArrayList<String>>().apply {
        value = DummyData().parishListSpinner
    }
    val parishListSpinner = _parishListSpinner

    private val pastoralList = MutableLiveData<ArrayList<PastoralTeam>>()
//    val pastoralList: MutableLiveData<ArrayList<PastoralTeam>>
//    get() = _pastoralList

    fun initilaizePastorlTeam() {
        val list = ArrayList<PastoralTeam>()
        pastoralList.value = list
    }

    fun addPastoralTeam(pastoralTeam: PastoralTeam) {
        val currenltList = pastoralList.value ?: ArrayList()
        currenltList.add(pastoralTeam)
        pastoralList.value = currenltList
    }

    fun getPastoralList(): LiveData<ArrayList<PastoralTeam>> {
        return pastoralList
    }

    fun removePastoralteam(position: Int) {
        pastoralList.value?.removeAt(position)
    }

}