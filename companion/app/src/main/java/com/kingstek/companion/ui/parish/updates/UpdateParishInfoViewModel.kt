package com.kingstek.companion.ui.parish.updates

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.SetOptions
import com.kingstek.companion.dummy_data.DummyData
import com.kingstek.companion.dummy_data.ParishModel
import com.kingstek.companion.models.parish.ImageModel
import com.kingstek.companion.models.parish.ImageModelUrl
import com.kingstek.companion.models.parish.Parish
import com.kingstek.companion.models.parish.PastoralTeam
import com.kingstek.companion.ui.BaseViewModel
import com.kingstek.companion.utils.Constants
import com.kingstek.companion.utils.Constants.PARISH
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UpdateParishInfoViewModel : BaseViewModel() {

//    private val _parishName =  MutableLiveData<String>()
//    val parishName: MutableLiveData<String>
//        get() = _parishName
//
//    private val _address =  MutableLiveData<String>()
//    val address: MutableLiveData<String>
//        get() = _address
//
//    private val _website =  MutableLiveData<String>()
//    val website: MutableLiveData<String>
//        get() = _website
//
//    private val _diocese =  MutableLiveData<String>()
//    val diocese: MutableLiveData<String>
//        get() = _diocese
//
//    private val _deanery =  MutableLiveData<String>()
//    val deanery: MutableLiveData<String>
//        get() = _deanery

    private val _parishModel = MutableLiveData<Parish>()
    val parishModel: MutableLiveData<Parish>
        get() = _parishModel

    private val _parishListSpinner = MutableLiveData<ArrayList<String>>().apply {
        value = DummyData().parishListSpinner
    }
    val parishListSpinner = _parishListSpinner

    private val _dioceseListSpinner = MutableLiveData<ArrayList<String>>().apply {
        value = DummyData().dioceseListSpinner
    }
    val dioceseListSpinner = _dioceseListSpinner

    private val pastoralList = MutableLiveData<ArrayList<PastoralTeam>>()

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

    private val parishImageList = MutableLiveData<ArrayList<ImageModel>>()

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

    private val parishImgListUrl = MutableLiveData<ArrayList<ImageModelUrl>>()

    fun addParishImageListUrl(imageList: ArrayList<ImageModelUrl>) {
//        val currentImgList = parishImgListUrl.value ?: ArrayList()
//        currentImgList.add(imageList)
        parishImgListUrl.value = imageList
    }

    fun getParishImageListUrl(): LiveData<ArrayList<ImageModelUrl>> {
        return parishImgListUrl
    }

    fun uploadParishImg(context: Context) {

        //upload images
        CoroutineScope(Dispatchers.IO).launch {

            getParishImageList().value?.forEach{
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it.imageUri)

                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
                val imageData = stream.toByteArray()

                val imageRef = mStorageRef.child("images/${it.title}.jpg")
                val uploadTask = imageRef.putBytes(imageData)
                Tasks.await(uploadTask)

                val imageUriTask = imageRef.downloadUrl
                val imageUrl = Tasks.await(imageUriTask).toString()

                val parishImageData = hashMapOf(
                    "title" to it.title,
                    "image_url" to imageUrl
                )

                val collectionRef = mFirestore.collection("myCollection")
                collectionRef.document().set(parishImageData)

            }
        }

    }

    fun uploadParishInfo(context: Context) {
        //upload images

        val tempImgList = ArrayList<ImageModelUrl>()

        CoroutineScope(Dispatchers.IO).launch {

            getParishImageList().value?.forEach {
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it.imageUri)

                //todo compress images to very small size
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
                val imageData = stream.toByteArray()

                val imageRef = mStorageRef.child("images/${it.title}.jpg")
                val uploadTask = imageRef.putBytes(imageData)
                Tasks.await(uploadTask)

                val imageUriTask = imageRef.downloadUrl
                val imageUrl = Tasks.await(imageUriTask).toString()

                val parishImageData = ImageModelUrl(
                    imageUrl,
                    it.title
                )

                tempImgList.add(parishImageData)
            }

            withContext(Dispatchers.Main) {
                pushToFireBase(tempImgList)
            }

        }

    }

    private fun pushToFireBase(tempImgList: ArrayList<ImageModelUrl>) {

        addParishImageListUrl(tempImgList)

        Log.d("Parish Model", parishModel.toString())
        //upload parish info
        mFirestore.collection(Constants.PARISH)
            .document(parishModel.value?.parishName!!)
            .set(parishModel, SetOptions.merge())
            .addOnSuccessListener {

            }
            .addOnFailureListener {
                Log.d("ERROR PARISH", it.message.toString(), it)
            }
    }



    suspend fun getDeanaryList(): List<String> = suspendCoroutine  { continuation ->

        mFirestore.collection(Constants.DIOCESE)
            .get()
            .addOnSuccessListener {
//                diocesName.value = it.documents[0].data?.get("dioceseName") as String
                deaneryList.value = it.documents[0].data?.get("dioceseList") as List<String>

                continuation.resume(deaneryList.value!!)

                Log.d("resulting deanery", deaneryList.value.toString())
                Log.d("resulting dioses", it.documents.toString())
                Log.d("resulting dioses", it.documents.get(0).id)
                Log.d("resulting dioses", it.documents.get(0).data.toString())
                Log.d("resulting dioses", it.documents.get(0).data?.get("dioceseList").toString())
            }
            .addOnFailureListener {
                Log.d("ERROR DOCESES", it.message.toString(), it)
                continuation.resumeWithException(it)
            }
    }

//    fun getParishList() {
//
//    }

}