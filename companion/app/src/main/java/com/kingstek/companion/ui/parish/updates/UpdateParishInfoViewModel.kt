package com.kingstek.companion.ui.parish.updates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kingstek.companion.dummy_data.DummyData
import com.kingstek.companion.dummy_data.ParishModel
import com.kingstek.companion.models.parish.ImageModel
import com.kingstek.companion.models.parish.PastoralTeam
import com.kingstek.companion.ui.BaseViewModel

class UpdateParishInfoViewModel : BaseViewModel() {

    private val _parishListSpinner = MutableLiveData<ArrayList<String>>().apply {
        value = DummyData().parishListSpinner
    }
    val parishListSpinner = _parishListSpinner

    private val pastoralList = MutableLiveData<ArrayList<PastoralTeam>>()
    private val parishImageList = MutableLiveData<ArrayList<ImageModel>>()
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

    fun initilaizeParishImageList() {
        val imgList = ArrayList<ImageModel>()
        parishImageList.value = imgList
    }

    fun addParishImageList(imageList: ImageModel) {
        val currentImgList = parishImageList.value ?: ArrayList()
        currentImgList.add(imageList)
        parishImageList.value = currentImgList
    }


    fun getParishImageList(): LiveData<ArrayList<ImageModel>> {
        return parishImageList
    }

    fun removeParishImageList(position: Int){
        parishImageList.value?.removeAt(position)
    }

    fun getDiocesList() {

    }

    fun getDeanaryList() {

    }

}