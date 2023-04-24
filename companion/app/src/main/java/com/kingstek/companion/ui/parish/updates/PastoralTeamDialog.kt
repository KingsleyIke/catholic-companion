package com.kingstek.companion.ui.parish.updates

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.kingstek.companion.R
import com.kingstek.companion.databinding.PastoralTeamBinding
import com.kingstek.companion.databinding.PastorialDialogLayoutBinding
import com.kingstek.companion.models.parish.PastoralTeam

class PastoralTeamDialog(val viewModel: UpdateParishInfoViewModel, val pastoralAdapter: PastoralAdapter): DialogFragment() {

    private lateinit var binding: PastorialDialogLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       binding = PastorialDialogLayoutBinding.inflate(layoutInflater)
        val view = binding.root


        //todo fix cancelable not working fine
        return AlertDialog.Builder(requireContext())
            .setTitle("Enter Name")
            .setView(view)
            .setNegativeButton("Cancel") { _, _ ->
                // Dialog was cancelled
            }
            .setPositiveButton("ADD") { _, _ ->

                if(!validatePastoralTeam()){
                    Toast.makeText(requireContext(), "All fields must be filled", Toast.LENGTH_LONG).show()
                } else {
                    val pastoralTeam = PastoralTeam(
                binding.etPosition.text.toString(),
                binding.etTitle.text.toString(),
                binding.etName.text.toString(),
                binding.etPhoneNumber.text.toString()
            )
                    viewModel.addPastoralTeam(pastoralTeam)
                    pastoralAdapter.notifyDataSetChanged()
                }

            }
            .setCancelable(false)
            .create()
    }

        fun validatePastoralTeam(): Boolean {

        if(binding.etTitle.text?.isEmpty()!!) {
            binding.tilTitle.error = "Field must not be empty"
            return false
        }

        binding.tilTitle.error = null

        if(binding.etName.text?.isEmpty()!!) {
            binding.tilName.error = "Field must not be empty"
            return false
        }

        binding.tilName.error = null

        if(binding.etPosition.text?.isEmpty()!!) {
            binding.tilPosition.error = "Field must not be empty"
            return false
        }

        binding.tilPosition.error = null

        if(binding.etPhoneNumber.text?.isEmpty()!!) {
            binding.tilPhonenumber.error = "Field must not be empty"
            return false
        }

        binding.tilPhonenumber.error = null

        return true
    }
}