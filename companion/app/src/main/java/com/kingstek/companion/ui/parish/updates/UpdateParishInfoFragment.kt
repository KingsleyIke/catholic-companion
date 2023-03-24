package com.kingstek.companion.ui.parish.updates

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentUpdateParishInfoBinding
import com.kingstek.companion.models.parish.PastoralTeam
import com.kingstek.companion.onItemClickListener


class UpdateParishInfoFragment : Fragment() {

    private var _binding: FragmentUpdateParishInfoBinding? = null
    private val binding get() =  _binding!!
    private lateinit var viewModel: UpdateParishInfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateParishInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this)[UpdateParishInfoViewModel::class.java]

        //todo debug fragment name title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Update Parish Info"


        /**
         * ParishList Spiner
         */
        val parishSpinner: Spinner = binding.spParishListSpinner
        val timeSpinner: Spinner = binding.spParishListSpinner

        binding.etParishName.setOnClickListener{
            binding.spParishListSpinner.performClick()
        }

        binding.spParishListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Get the selected item from the adapter
                val selectedItem = parent.getItemAtPosition(position).toString()
                val editable = Editable.Factory.getInstance().newEditable(selectedItem)
                binding.etParishName.text = editable
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
                // to if nothing selected display error
            }
        }

        //spinner adapter
        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            viewModel.parishListSpinner.value!!
        )

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        parishSpinner.adapter = dataAdapter

        timeSpinner.adapter = dataAdapter


        /**
         * Pastoral team setup
         */
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


        viewModel.initilaizePastorlTeam()

        val pastoralRecyclerView = binding.rvPastoralTeam
        val pastoralAdapter = PastoralAdapter(viewModel.getPastoralList(), object : onItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                viewModel.removePastoralteam(position)
            }
        })


        pastoralRecyclerView.adapter = pastoralAdapter
        pastoralRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.tvAddBtn.setOnClickListener {

            if (!validatePastoralTeam()) {
                return@setOnClickListener
            }

            val pastoralTeam = PastoralTeam(
                binding.etPosition.text.toString(),
                binding.etTitle.text.toString(),
                binding.etName.text.toString(),
                binding.etPhoneNumber.text.toString()
            )

            viewModel.addPastoralTeam(pastoralTeam)

            // is this necessary already being done in adapter
            pastoralAdapter.notifyDataSetChanged()

            val emptyString = ""
            val editable = Editable.Factory.getInstance().newEditable(emptyString)

            binding.etPosition.text = editable
            binding.etTitle.text = editable
            binding.etName.text = editable
            binding.etPhoneNumber.text = editable
        }




        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}