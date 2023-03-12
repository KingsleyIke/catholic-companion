package com.kingstek.companion.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentForgetPasswordBinding


class ForgetPasswordFragment : Fragment() {

    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ForgetPasswordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ForgetPasswordViewModel::class.java]

        binding.progressBar.hide()

        binding.tvReturnToLoginScreen.setOnClickListener {
            it.findNavController().navigate(R.id.logInFragment)
        }

        binding.btnRetrievePassword.setOnClickListener {

            viewModel.email.value = binding.etEmail.text.toString().trim()

            if (!validateEmail()){
                Toast.makeText(requireContext(), "Make sure all fields are filled correctly", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            binding.progressBar.smoothToShow()

            viewModel.forgetPassword()

            viewModel.forgetPasswordSuccess.observe(viewLifecycleOwner) {
                if(!it) {
                    Toast.makeText(requireContext(), viewModel.forgetPasswordErrorMessage.value.toString(), Toast.LENGTH_LONG).show()
                } else {
                    //todo display email app opening options
                    Toast.makeText(requireContext(), "Password reset Email sent - Check connected Email Address", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.logInFragment)
                }
            }

            binding.progressBar.smoothToHide()

        }


        return binding.root
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