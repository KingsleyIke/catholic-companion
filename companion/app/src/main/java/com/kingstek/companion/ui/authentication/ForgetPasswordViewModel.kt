package com.kingstek.companion.ui.authentication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.kingstek.companion.ui.BaseViewModel

class ForgetPasswordViewModel : BaseViewModel() {

    private val _forgetPasswordSuccess = MutableLiveData<Boolean>()
    val forgetPasswordSuccess: MutableLiveData<Boolean>
        get() = _forgetPasswordSuccess

    private val _forgetPasswordErrorMessage = MutableLiveData<String>()
    val forgetPasswordErrorMessage: MutableLiveData<String>
        get() = _forgetPasswordErrorMessage

    fun forgetPassword() {
        if (email.value == null) {
            forgetPasswordErrorMessage.value = "Error in email or password entered"
            Log.e("ERRROR", "errror")
            return
        }

        FirebaseAuth.getInstance().sendPasswordResetEmail(email.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    forgetPasswordSuccess.postValue(true)
                } else{
                    forgetPasswordSuccess.postValue(false)
                    forgetPasswordErrorMessage.value = task.exception?.message.toString()
                    Log.e("Error Message", task.exception?.message.toString())
                }
            }
    }
}