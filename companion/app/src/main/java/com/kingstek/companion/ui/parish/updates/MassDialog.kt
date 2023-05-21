package com.kingstek.companion.ui.parish.updates

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.kingstek.companion.R
import com.kingstek.companion.databinding.MassDialogBinding
import com.kingstek.companion.databinding.PastoralTeamBinding
import com.kingstek.companion.databinding.PastorialDialogLayoutBinding
import com.kingstek.companion.models.parish.PastoralTeam
import com.kingstek.companion.models.parish.WeekMasses

class MassDialog(val viewModel: UpdateParishInfoViewModel, val massAdapter: MassAdapter, val sMass: Boolean): DialogFragment() {

    private lateinit var binding: MassDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       binding = MassDialogBinding.inflate(layoutInflater)
        val view = binding.root


        if (sMass) {
            binding.tilMassTimes.visibility = View.GONE
        }

        if (!sMass) {
            binding.tilTitle.hint = "Enter Day (Monday)"
        }

        //todo fix cancelable not working fine
        return AlertDialog.Builder(requireContext())
            .setTitle("Add Mass")
            .setView(view)
            .setNegativeButton("Cancel") { _, _ ->
                // Dialog was cancelled
            }
            .setPositiveButton("ADD") { _, _ ->

                if(!validatePastoralTeam()){
                    Toast.makeText(requireContext(), "All fields must be filled", Toast.LENGTH_LONG).show()
                } else {

                    if (sMass) {
                        val sundayMass = binding.etTitle.text.toString()
                        viewModel.addSundayMass(sundayMass)
                        massAdapter.notifyDataSetChanged()
                    } else {
                        val weekMasses = WeekMasses(
                            binding.etTitle.text.toString(),
                            binding.etMassTimes.text.toString()
                        )
                        viewModel.addWeekDayMass(weekMasses)
                        massAdapter.notifyDataSetChanged()
                    }
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

            if(!sMass) {
                if(binding.etMassTimes.text?.isEmpty()!!) {
                    binding.etMassTimes.error = "Field must not be empty"
                    return false
                }

                binding.tilMassTimes.error = null
            }
        return true
    }
}