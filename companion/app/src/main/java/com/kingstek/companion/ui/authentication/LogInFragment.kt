package com.kingstek.companion.ui.authentication

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LogInViewModel

    //todo on completion of login display dialog to tell user what can be done and if to return to home or move to parish announce update screen, or gallery update screen
    //todo show loading Icon - Use AVL LAODER
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LogInViewModel::class.java]

        binding.progressBar.hide()

        binding.tvForgetPassword.setOnClickListener {
         it.findNavController().navigate(R.id.forgetPasswordFragment)
        }

        binding.btnSignUpToLogin.setOnClickListener {
            it.findNavController().navigate(R.id.singInFragment)
        }

        binding.tvSignUpToLogin.setOnClickListener {
            it.findNavController().navigate(R.id.singInFragment)
        }

        binding.btnSignUpToLogin.setOnClickListener {
            it.findNavController().navigate(R.id.singInFragment)
        }

        var passwordVisibility = false
        //todo fix password visibility change

        binding.passwordVisibility.setOnClickListener {

//            if (!passwordVisibility) {
            it.setBackgroundResource(R.drawable.ic_baseline_remove_red_eye_24)
            binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordVisibility = true
            return@setOnClickListener
//        }
            }


        binding.btnLogIn.setOnClickListener {

            setData()

            if(!authenticateValues()) {
                Toast.makeText(requireContext(), "Make sure all fields are filled correctly", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            binding.progressBar.show()

            viewModel.login()

            viewModel.loginSuccess.observe(viewLifecycleOwner) {
                if (!it) {
                    Toast.makeText(requireContext(), viewModel.loginErrorMessage.value.toString(), Toast.LENGTH_LONG).show()
                    val valuse = viewModel.loginErrorMessage.value.toString()
                    Log.d("VA", valuse)
                    Log.d("VA IT", "$it")
                } else {
                    Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_LONG).show()

                    //todo change gallery colors
                    //todo return individuals to where they are coming from
                    val alertDialog = AlertDialog.Builder(requireContext())
                    alertDialog.setTitle("Where To")
                    alertDialog.setItems(arrayOf<CharSequence>(
                        "Return to Home Screen",
                        "Update Parish Info",
                        "Update Parish Gallery"
                    ),
                        DialogInterface.OnClickListener { dialog, which ->
                            when (which) {
                                0 -> {
                                    findNavController().navigate(R.id.nav_home)
                                }
                                1 -> {
                                    findNavController().navigate(R.id.updateParishInfoFragment)
                                }
                                2 -> {
                                    findNavController().navigate(R.id.updateGalleryFragment)
                                }
                            }
                        })
                    alertDialog.show()
//                    findNavController().navigate(R.id.logInFragment)
                }
            }

            binding.progressBar.hide()
        }

        return binding.root
    }

    private fun setData() {
        viewModel.email.value = binding.etEmail.text.toString()
        viewModel.password.value = binding.etPassword.text.toString()
    }

    fun authenticateValues(): Boolean {

        if ( validateEmail() &&
            validatePassword()) {
            return true
        }

        return false
    }
    fun validateEmail(): Boolean {
        if (binding.etEmail.text.isEmpty()) {
            binding.emailErrorMessage.text = "Email must not be empty"
            return false
        }

        if (binding.etEmail.text.length < 3 ) {
            binding.emailErrorMessage.text = "Email must be more than 2 characters"
            return false
        }

        if (!binding.etEmail.text.contains("[@.]".toRegex())) {
            binding.emailErrorMessage.text = "Enter valid email address"
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

//        if (!binding.etPassword.text.contains("[A-Z]".toRegex()) || !binding.etPassword.text.contains("[a-z]".toRegex())) {
//            binding.passwordErrorMessage.text = "Password must contain letters"
//            return false
//        }
        if (!binding.etPassword.text.contains("[0-9]".toRegex())) {
            binding.passwordErrorMessage.text = "Password must contain numbers"
            return false
        }

        binding.passwordErrorMessage.visibility = View.INVISIBLE
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