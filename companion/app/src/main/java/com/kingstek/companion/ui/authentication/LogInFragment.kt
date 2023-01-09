package com.kingstek.companion.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentForgetPasswordBinding
import com.kingstek.companion.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    //todo authenticate email and password with corresponding messages whine empty or does not meet requirements or passwrod policy
    //todo on colpletion of login display dialog to tell user what can be done and if to return to home of move to parish announce ubdate screen, or gallery update screen
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLogInBinding.inflate(inflater, container, false)

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

        return binding.root
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