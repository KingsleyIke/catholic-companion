package com.kingstek.companion.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.kingstek.companion.dummy_data.DummyData
import com.kingstek.companion.dummy_data.NewsModel
import com.kingstek.companion.dummy_data.ParishModel
import com.kingstek.companion.models.diocese.Diocese
import com.kingstek.companion.models.parish.Parish
import com.kingstek.companion.utils.Constants
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//A base viewModel to load all como=mon codes across all viewModels
open class BaseViewModel : ViewModel() {

    //TODO handle check if user is loginned here, save user name and id in local db (phone)


    val mFirestore = FirebaseFirestore.getInstance()
    val mStorageRef = FirebaseStorage.getInstance().reference
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser

    private val _email = MutableLiveData<String>()
    val email: MutableLiveData<String>
    get() = _email

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String>
    get() = _password

    private val _loggegIn = MutableLiveData<Boolean>()
    val loggegIn: MutableLiveData<Boolean>
        get() = _loggegIn

    private val _diocesName = MutableLiveData<String>()
    val diocesName: MutableLiveData<String>
        get() = _diocesName

    private val _deaneryList = MutableLiveData<List<String>>()
    val deaneryList: MutableLiveData<List<String>>
        get() = _deaneryList

    private val _parishList = MutableLiveData<List<Parish>>()
    val parishList: MutableLiveData<List<Parish>>
        get() = _parishList


    val data = DummyData()

    private val _newsList = MutableLiveData<List<NewsModel>>().apply {
        value = data.newsList
    }
    val newsList = _newsList

//    private val _parishList = MutableLiveData<List<ParishModel>>()
//    val parishList: MutableLiveData<List<ParishModel>>
//        get() = _parishList

//    private val _parishList =  MutableLiveData<List<ParishModel>>().apply {
//        value = data.parishList
//    }
//    val parishList = _parishList

    fun isUserSignedIn(): Boolean {
        if(currentUser != null) {
            Log.d("User", currentUser.uid)
            loggegIn.value = true
            return true
        }

        loggegIn.value = false
        return false
    }

    fun logOut() {
        FirebaseAuth.getInstance().signOut()
    }

    suspend fun getParishList(): List<Parish> = suspendCoroutine  { continuation ->

        mFirestore.collection(Constants.PARISH)
            .get()
            .addOnSuccessListener {
//                diocesName.value = it.documents[0].data?.get("dioceseName") as String
                parishList.value = it.documents as List<Parish>

                continuation.resume(parishList.value!!)

                Log.d("resulting deanery", parishList.value.toString())
                Log.d("resulting dioses", it.documents.toString())
                Log.d("resulting dioses", it.documents.get(0).id)
                Log.d("resulting dioses", it.documents.get(0).data.toString())
//                Log.d("resulting dioses", it.documents.get(0).data?.get("dioceseList").toString())
            }
            .addOnFailureListener {
                Log.d("ERROR DOCESES", it.message.toString(), it)
                continuation.resumeWithException(it)
            }
    }

    fun getDioceseList() {
        mFirestore.collection(Constants.DIOCESE)
            .get()
            .addOnSuccessListener {
                diocesName.value = it.documents[0].data?.get("dioceseName") as String
                deaneryList.value = it.documents[0].data?.get("dioceseList") as List<String>

                Log.d("resulting deanery", deaneryList.value.toString())
                Log.d("resulting dioses", it.documents.toString())
                Log.d("resulting dioses", it.documents.get(0).id)
                Log.d("resulting dioses", it.documents.get(0).data.toString())
                Log.d("resulting dioses", it.documents.get(0).data?.get("dioceseList").toString())
            }
            .addOnFailureListener {
                Log.d("ERROR DOCESES", it.message.toString(), it)
            }
        Log.d("end test", "endsss")
    }

    fun pushdioces() {

        val archdioces = Diocese("Lagos Archdioces",
            listOf("Agege Deanery",
                "Ajah Deanery",
                "Apapa Deanery","Badagry Deanery","Epe Deanery",
                "Festac Deanery","Iba Deanery","Ijanikin Deanery",
                "Ijede Deanery","Ikeja Deanery","Ikorodu Deanery",
                "Ilemba Deanery","Ipaja Deanery","Isolo Deanery",
                "Lagos Island Deanery","Lekki Deanery","Maryland Deanery",
                "Satellite Deanery","Surulere Deanery","Yaba Deanery"))

        mFirestore.collection(Constants.DIOCESE)
            .document("Lagos Diocese")
            .set(archdioces, SetOptions.merge())
            .addOnSuccessListener {

            }
            .addOnFailureListener {
                Log.d("ERROR DOCESES", it.message.toString(), it)
            }
    }

}