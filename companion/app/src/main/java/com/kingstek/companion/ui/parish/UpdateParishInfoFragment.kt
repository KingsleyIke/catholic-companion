package com.kingstek.companion.ui.parish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kingstek.companion.databinding.FragmentUpdateParishInfoBinding

class UpdateParishInfoFragment : Fragment() {

    private var _binding: FragmentUpdateParishInfoBinding? = null
    private val binding get() =  _binding!!
    private lateinit var updateParishInfoViewModel: UpdateParishInfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateParishInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        updateParishInfoViewModel = ViewModelProvider(this)[UpdateParishInfoViewModel::class.java]

        //todo debug fragment name title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Update Parish Info"


        val parishSpinner: Spinner = binding.parishSpinner
        val timeSpinner: Spinner = binding.parishSpinner

        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            updateParishInfoViewModel.parishListSpinner.value!!
        )

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        parishSpinner.adapter = dataAdapter

        timeSpinner.adapter = dataAdapter



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}