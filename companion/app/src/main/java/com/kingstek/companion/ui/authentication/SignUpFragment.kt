package com.kingstek.companion.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentSignUpBinding
import com.kingstek.companion.utils.ProgressDialog

class SingInFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignUpViewModel
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.btnReturnToLogin.setOnClickListener{
            it.findNavController().navigate(R.id.logInFragment)
        }

        binding.tvReturnToLogin.setOnClickListener{
            it.findNavController().navigate(R.id.logInFragment)
        }


        progressDialog = ProgressDialog(requireContext())

        binding.btnSingUp.setOnClickListener {

            progressDialog.showProgressDialog("Loading")

            setData()
            viewModel.signUp()

            progressDialog.hideProgressDialog()
        }

        return binding.root
    }

    fun setData () {
        viewModel.email?.value = binding.etEmailAddress.text.toString()
        viewModel.firstName.value = binding.etFirstName.text.toString()
        viewModel.lastName?.value = binding.etLastName.text.toString()
        viewModel.userName?.value = binding.etUsernameName.text.toString()
        viewModel.password?.value = binding.etConfrimPassword.text.toString()
    }

    fun authenticateValues() {

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