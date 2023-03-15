package com.kingstek.companion.ui.authentication

import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentSignUpBinding
import com.kingstek.companion.utils.ProgressDialog

class SingInFragment : Fragment() {

    //TODO add loading Icon - Progrees bar
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignUpViewModel
//    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.progressBar.hide()

        binding.btnReturnToLogin.setOnClickListener {
            it.findNavController().navigate(R.id.logInFragment)
        }

        binding.tvReturnToLogin.setOnClickListener {
            it.findNavController().navigate(R.id.logInFragment)
        }

        var passwordVisibility = false
        var confirmPasswordVisibility = false
        //todo fix password visibility change

        binding.passwordVisibility.setOnClickListener {

//            if (!passwordVisibility) {
                it.setBackgroundResource(R.drawable.ic_baseline_remove_red_eye_24)
                binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passwordVisibility = true
                return@setOnClickListener
//            }

//            if(passwordVisibility) {
//                it.setBackgroundResource(R.drawable.ic_baseline_remove_red_eye_24)
//                binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
//                passwordVisibility = false
//                return@setOnClickListener
//            }

        }

        binding.confirmPasswordVisibility.setOnClickListener {

//            if (!confirmPasswordVisibility) {
            it.setBackgroundResource(R.drawable.ic_baseline_remove_red_eye_24)
            binding.etConfirmPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            confirmPasswordVisibility = true
            return@setOnClickListener
//            }

//            if(confirmPasswordVisibility) {
//                it.setBackgroundResource(R.drawable.ic_baseline_remove_red_eye_24)
//                binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
//                passwordVisibility = false
//                return@setOnClickListener
//            }

        }

        binding.btnSingUp.setOnClickListener { view ->

//            binding.progressBar.visibility = View.VISIBLE

            setData()

            if (!authenticateValues()) {
                Toast.makeText(requireContext(), "Make sure all fields are filled correctly", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            binding.progressBar.smoothToShow()

            viewModel.signUp()

            viewModel.registerSuccess.observe(viewLifecycleOwner) {
                if (!it) {
                    Toast.makeText(requireContext(), viewModel.registerErrorMessage.value.toString(), Toast.LENGTH_LONG).show()
                    val valuse = viewModel.registerErrorMessage.value.toString()
                    Log.d("VA", valuse)
                    Log.d("VA IT", "$it")
                } else {
                    Toast.makeText(requireContext(), "Profile created Successfully", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.logInFragment)
                }
            }

            binding.progressBar.smoothToHide()
        }

        return binding.root
    }

    fun setData() {
        viewModel.email.value = binding.etEmailAddress.text.toString()
        viewModel.password.value = binding.etConfirmPassword.text.toString()

        viewModel.firstName.postValue(binding.etFirstName.text.toString())
        viewModel.lastName.postValue(binding.etLastName.text.toString())
        viewModel.userName.postValue(binding.etUsernameName.text.toString())
    }


    //todo move all logic to viewmodel and optimize code
    fun authenticateValues(): Boolean {

        if (validateFirstName() &&
            validateLastName() &&
            validateUserName() &&
            validateEmail() &&
            validatePassword() &&
            validateConfirmPassword()
        ) {
            return true
        }

        return false
    }

    fun validateFirstName() : Boolean {

        if (binding.etFirstName.text.isEmpty()) {
            binding.firstNameErrorMessage.text = "First name must not be empty"
            return false
        }

        if (binding.etFirstName.text.length < 3 ) {
            binding.firstNameErrorMessage.text = "First name must be more than 2 characters"
            return false
        }

        if (binding.etFirstName.text.contains("[0-9]".toRegex()) ) {
            binding.firstNameErrorMessage.text = "First name must not have number values"
            return false
        }

        if (binding.etFirstName.text.contains("[!\"#$%&'()*+,./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            binding.firstNameErrorMessage.text = "First name must not contain special characters"
            return false        }

        binding.firstNameErrorMessage.visibility = View.INVISIBLE
        return true
    }

    fun validateLastName(): Boolean {
        if (binding.etLastName.text.isEmpty()) {
            binding.lastNameErrorMessage.text = "Last name must not be empty"
            return false
        }

        if (binding.etLastName.text.length < 3 ) {
            binding.lastNameErrorMessage.text = "Last name must be more than 2 characters"
            return false
        }

        if (binding.etLastName.text.contains("[0-9]".toRegex()) ) {
            binding.lastNameErrorMessage.text = "Last name must not have number values"
            return false
        }

        if (binding.etLastName.text.contains("[!\"#$%&'()*+,./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            binding.lastNameErrorMessage.text = "Last name must not contain special characters"
            return false        }

        binding.lastNameErrorMessage.visibility = View.INVISIBLE
        return true
    }

    //todo check if it is possible to check other usernames on firebase
    //re-evaluate - any need for username?
    fun validateUserName(): Boolean {
        if (binding.etUsernameName.text.isEmpty()) {
            binding.userNameErrorMessage.text = "User name must not be empty"
            return false
        }

        if (binding.etUsernameName.text.length < 3 ) {
            binding.userNameErrorMessage.text = "User name must be more than 2 characters"
            return false
        }

//        if (binding.etLastName.text.contains("[0-9]".toRegex()) ) {
//            binding.lastNameErrorMessage.text = "Last name must not have number values"
//            return false
//        }
//
//        if (binding.etLastName.text.contains("[!\"#$%&'()*+,./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
//            binding.lastNameErrorMessage.text = "Last name must not contain special characters"
//            return false        }

        binding.userNameErrorMessage.visibility = View.INVISIBLE
        return true
    }

    fun validateEmail(): Boolean {
        if (binding.etEmailAddress.text.isEmpty()) {
            binding.userNameErrorMessage.text = "Email must not be empty"
            return false
        }

        if (binding.etEmailAddress.text.length < 3 ) {
            binding.userNameErrorMessage.text = "Email must be more than 2 characters"
            return false
        }

        if (!binding.etEmailAddress.text.contains("[@.]".toRegex())) {
            binding.userNameErrorMessage.text = "Enter valid email address"
            return false
        }

        binding.emailErrorMessage.visibility = View.INVISIBLE
        return true
    }


    fun validatePassword(): Boolean {
        if (binding.etPassword.text.isEmpty()) {
            binding.passwordErrorMessage.text = "Password must not be empty"
            return false
        }

        if (binding.etPassword.text.isEmpty()) {
            binding.passwordErrorMessage.text = "Password must be more than 5 characters"
            return false
        }

        if (!binding.etPassword.text.contains("[A-Za-z]".toRegex())) {
            binding.passwordErrorMessage.text = "Password must contain letters"
            return false
        }
        if (!binding.etPassword.text.contains("[0-9]".toRegex())) {
            binding.passwordErrorMessage.text = "Password must contain numbers"
            return false
        }

        binding.passwordErrorMessage.visibility = View.INVISIBLE
        return true
    }

    fun validateConfirmPassword(): Boolean{

        if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
            binding.passwordConfirmationErrorMessage.text = "Password must match to confirm password"
            return false
        }

        binding.passwordConfirmationErrorMessage.visibility = View.INVISIBLE
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}