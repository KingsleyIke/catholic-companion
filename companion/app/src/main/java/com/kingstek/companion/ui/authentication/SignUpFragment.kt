package com.kingstek.companion.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentSignUpBinding
import com.kingstek.companion.utils.ProgressDialog

class SingInFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignUpViewModel
//    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.btnReturnToLogin.setOnClickListener {
            it.findNavController().navigate(R.id.logInFragment)
        }

        binding.tvReturnToLogin.setOnClickListener {
            it.findNavController().navigate(R.id.logInFragment)
        }

        binding.btnSingUp.setOnClickListener { view ->

            setData()
            if (!authenticateValues()) {
                return@setOnClickListener
            }

            val snackBar = Snackbar.make(view, "Loading", Snackbar.LENGTH_LONG)
            snackBar.show()

            viewModel.signUp()
            viewModel.registerSuccess?.observe(viewLifecycleOwner) {
                if (!it) {
                    Toast.makeText(requireContext(), viewModel.registerErrorMessage?.value.toString(), Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), "Profile created Successfully", Toast.LENGTH_LONG).show()
                }
            }

//            if (viewModel.registerSuccess?.value == true) {
//                Toast.makeText(requireContext(), "Profile created Successfully", Toast.LENGTH_LONG).show()
//            }

            binding.progressBar.visibility = View.INVISIBLE
        }

        return binding.root
    }

    fun setData() {
        viewModel.email.value = binding.etEmailAddress.text.toString()
        viewModel.password.value = binding.etConfrimPassword.text.toString()

        viewModel.firstName.postValue(binding.etFirstName.text.toString())
        viewModel.lastName.postValue(binding.etLastName.text.toString())
        viewModel.userName.postValue(binding.etUsernameName.text.toString())
    }

    fun authenticateValues(): Boolean {

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