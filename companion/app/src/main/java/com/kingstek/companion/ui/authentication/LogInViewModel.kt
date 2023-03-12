package com.kingstek.companion.ui.authentication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.kingstek.companion.ui.BaseViewModel

class LogInViewModel: BaseViewModel() {

    //TODO on return from sign up display email on enter email edittext
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: MutableLiveData<Boolean>
        get() = _loginSuccess

    private val _loginErrorMessage =  MutableLiveData<String>()
    val loginErrorMessage: MutableLiveData<String>
        get() = _loginErrorMessage


    fun login() {
        if (email.value == null || password.value == null) {
            loginErrorMessage.value = "Error in email or password entered"
            Log.e("ERRROR", "errror")
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    loginSuccess.postValue(true)

                } else {
                    loginSuccess.postValue(false)
                    loginErrorMessage.value = task.exception?.message.toString()
                    Log.e("Error msgsssss", task.exception!!.message.toString())

                }
            }
    }
}