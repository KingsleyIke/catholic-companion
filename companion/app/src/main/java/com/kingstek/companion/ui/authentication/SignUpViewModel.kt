package com.kingstek.companion.ui.authentication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.SetOptions
import com.kingstek.companion.models.User
import com.kingstek.companion.ui.BaseViewModel
import com.kingstek.companion.utils.Constants

class SignUpViewModel : BaseViewModel() {


    var firstName =  MutableLiveData ("")
    var lastName: MutableLiveData<String>? = null
    var userName: MutableLiveData<String>? = null
    var profileComplete: MutableLiveData<Int>? = null
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
                        lastName?.value!!.trim {it <= ' '},
                        userName?.value!!.trim {it <= ' '},
                        email?.value!!.trim {it <= ' '}
                    )

                    registerUser(user)
//                    registerSuccess?.value = true

                } else {

//                    registerSuccess?.value = false
                    registerErrorMessage?.value = task.exception!!.message.toString()

                }
            }
    }

    private fun registerUser (userInfo: User) {

        mFirestore.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

            }
            .addOnFailureListener {
//                registerSuccess?.value = false
                registerErrorMessage?.value = it.message.toString()
            }
    }
}