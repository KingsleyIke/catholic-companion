package com.kingstek.companion.ui.authentication

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.SetOptions
import com.kingstek.companion.models.User
import com.kingstek.companion.ui.BaseViewModel
import com.kingstek.companion.utils.Constants

class SignUpViewModel : BaseViewModel() {


    private val _firstName =  MutableLiveData<String>()
    val firstName: MutableLiveData<String>
    get() = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: MutableLiveData<String>
    get() = _lastName

    private val _userName = MutableLiveData<String>()
    val userName: MutableLiveData<String>
    get() = _userName

    private val _profileComplete = MutableLiveData<Int>()
    val profileComplete: MutableLiveData<Int>
    get() = _profileComplete

    var registerSuccess: MutableLiveData<Boolean>? = null
    var registerErrorMessage: MutableLiveData<String>? = null

    fun signUp() {

        if (email?.value == null || password?.value == null) {
            registerErrorMessage?.value = "Error in email or password entered"
            Log.e("ERRROR", "errror")
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email?.value!!, password?.value!!)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {

                    val firebaseUser: FirebaseUser = task.result!!.user!!

                    val user = User(
                        firebaseUser.uid,
                        firstName.value!!.trim {it <= ' '},
                        lastName.value!!.trim {it <= ' '},
                        userName.value!!.trim {it <= ' '},
                        email.value!!.trim {it <= ' '}
                    )

                    registerUser(user)
                    registerSuccess?.value = true

                } else {

                    registerSuccess?.value = false
                    registerErrorMessage?.value = task.exception!!.message.toString()
                    Log.e("Error msg", task.exception!!.message.toString())

                }
            }
    }

    private fun registerUser (userInfo: User) {

        mFirestore.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                registerSuccess?.value = false

            }
            .addOnFailureListener {
//                registerSuccess?.value = false
                registerErrorMessage?.value = it.message.toString()
                Log.e("Error msg", it.message.toString())

            }
    }
}