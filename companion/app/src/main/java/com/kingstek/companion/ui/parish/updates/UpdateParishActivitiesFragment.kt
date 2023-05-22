package com.kingstek.companion.ui.parish.updates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kingstek.companion.databinding.FragmentUpdateMassBinding

//todo add office line to parish

class UpdateParishActivitiesFragment : Fragment() {

    private var _binding: FragmentUpdateMassBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UpdateParishActivitiesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateMassBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this)[UpdateParishActivitiesViewModel::class.java]

        return root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}